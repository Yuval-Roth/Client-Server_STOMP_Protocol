#include "../include/event.h"
#include "../include/json.hpp"
#include "UserData.h"
#include <iostream>
#include <fstream>
#include <string>
#include <map>
#include <vector>
#include <sstream>
using json = nlohmann::json;

Event::Event(string reporter, std::string team_a_name, std::string team_b_name, std::string name, int time,
             std::map<std::string, std::string> game_updates, std::map<std::string, std::string> team_a_updates,
             std::map<std::string, std::string> team_b_updates, std::string description)
    : reporter(reporter), team_a_name(team_a_name), team_b_name(team_b_name), name(name),
      time(time), game_updates(game_updates), team_a_updates(team_a_updates),
      team_b_updates(team_b_updates), description(description){}

const string &Event::get_reporter() const {
    return reporter;
}

const std::string &Event::get_team_a_name() const
{
    return this->team_a_name;
}

const std::string &Event::get_team_b_name() const
{
    return this->team_b_name;
}

const std::string &Event::get_name() const
{
    return this->name;
}

int Event::get_time() const
{
    return this->time;
}

const std::map<std::string, std::string> &Event::get_game_updates() const
{
    return this->game_updates;
}

const std::map<std::string, std::string> &Event::get_team_a_updates() const
{
    return this->team_a_updates;
}

const std::map<std::string, std::string> &Event::get_team_b_updates() const
{
    return this->team_b_updates;
}

const std::string &Event::get_description() const
{
    return this->description;
}

Event::Event(string gameName, const std::string &frame_body)
        : reporter(""), team_a_name(""), team_b_name(""), name(""), time(0), game_updates(),
          team_a_updates(), team_b_updates(), description("")
{

    istringstream iss(frame_body);
    string line;

    //reporter
    getline(iss, line);
    if (line.find("user:") != string::npos)
    {
        reporter = line.substr(line.find(":") + 2);
    }


    //team_a_name
    team_a_name = gameName.substr(0,gameName.find("_"));

    //team_b_name
    team_b_name = gameName.substr(gameName.find("_") + 1);
    getline(iss, line);
    getline(iss, line); // Remove the two lines containing the team names - they seem to put us off by 2 lines
    //name
    getline(iss, line);
    if (line.find("event name:") != string::npos)
    {
        name = line.substr(line.find(":") + 2);
    }

    //time
    getline(iss, line);
    if (line.find("time:") != string::npos)
    {
        try {
            time = stoi(line.substr(line.find(":") + 2));
        } catch (exception e) {
            time = 0;
        }
    }

    //general updates
    getline(iss, line);
    if (line.find("general game updates:") != string::npos)
    {
        while (getline(iss, line) && line.find("team a updates:") == string::npos)
        {
            int delimiter = line.find(":");
            string key = line.substr(0, delimiter);
            string value = line.substr(delimiter + 2);
            game_updates[key] = value;
        }
    }

    //team_a_updates
    if (line.find("team a updates:") != string::npos)
    {
        while (getline(iss, line) && line.find("team b updates:") == string::npos)
        {
            int delimiter = line.find(":");
            string key = line.substr(0, delimiter);
            string value = line.substr(delimiter + 2);
            team_a_updates[key] = value;
        }
    }

    //team_b_updates
    if (line.find("team b updates:") != string::npos)
    {
        while (getline(iss, line) && line.find("description:") == string::npos)
        {
            int delimiter = line.find(":");
            string key = line.substr(0, delimiter);
            string value = line.substr(delimiter + 2);
            team_b_updates[key] = value;
        }
    }

    //description
    if (line.find("description:") != string::npos)
    {
        getline(iss, line,'\0');
        description = line;
    }
}

string Event::toJson() {
    json j;
    j["user"] = reporter;
    j["team_a_name"] = team_a_name;
    j["team_b_name"] = team_b_name;
    j["name"] = name;
    j["time"] = time;
    j["game_updates"] = game_updates;
    j["team_a_updates"] = team_a_updates;
    j["team_b_updates"] = team_b_updates;
    j["description"] = description;
    return j.dump();
}

string Event::extractFrameBody() {
    string output = "";
    output += "user: " + reporter+"\n";
    output += "team_a_name: " + team_a_name+"\n";
    output += "team_b_name: " + team_b_name+"\n";
    output += "event name: " + name+"\n";
    output += "time:" + to_string(time)+"\n";
    output += "general game updates: \n";
    for (auto & gameUpdate : game_updates) {
        output += gameUpdate.first + ":" + gameUpdate.second + ",\n";
    }
    output += "team a updates: \n";
    for (auto & teamAUpdate : team_a_updates) {
        output += teamAUpdate.first + ":" + teamAUpdate.second + ",\n";
    }
    output += "team b updates: \n";
    for (auto & teamBUpdate : team_b_updates) {
        output += teamBUpdate.first + ":" + teamBUpdate.second + ",\n";
    }
    output += "description: \n" + description;
    return output;
}

const string Event::get_game_name() const {
    return team_a_name + "_" + team_b_name;
}

names_and_events parseEventsFile(std::string json_path)
{
    std::ifstream f(json_path);
    json data = json::parse(f);

    std::string team_a_name = data["team a"];
    std::string team_b_name = data["team b"];

    // run over all the events and convert them to Event objects
    std::vector<Event> events;
    for (auto &event : data["events"])
    {
        std::string name = event["event name"];
        int time = event["time"];
        std::string description = event["description"];
        std::map<std::string, std::string> game_updates;
        std::map<std::string, std::string> team_a_updates;
        std::map<std::string, std::string> team_b_updates;
        for (auto &update : event["general game updates"].items())
        {
            if (update.value().is_string())
                game_updates[update.key()] = update.value();
            else
                game_updates[update.key()] = update.value().dump();
        }

        for (auto &update : event["team a updates"].items())
        {
            if (update.value().is_string())
                team_a_updates[update.key()] = update.value();
            else
                team_a_updates[update.key()] = update.value().dump();
        }

        for (auto &update : event["team b updates"].items())
        {
            if (update.value().is_string())
                team_b_updates[update.key()] = update.value();
            else
                team_b_updates[update.key()] = update.value().dump();
        }
        
        events.push_back(Event(UserData::getInstance().getUserName(), team_a_name, team_b_name, name, time, game_updates, team_a_updates,
                               team_b_updates, description));
    }
    names_and_events events_and_names{team_a_name, team_b_name, events};

    return events_and_names;
}