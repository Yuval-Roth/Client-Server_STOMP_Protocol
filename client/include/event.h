#pragma once

using namespace std;

#include <string>
#include <iostream>
#include <map>
#include <vector>

class Event
{
    private:
        string reporter;
        // name of team a
        string team_a_name;
        // name of team b
        string team_b_name;
        // name of the event
        string name;
        // time of the event in seconds
        int time;
        // map of all the general game updates
        map<string, string> game_updates;
        // map of all team a updates the second type can be a string bool or int
        map<string, string> team_a_updates;
        // map of all team b updates
        map<string, string> team_b_updates;
        // description of the event
        string description;

    public:
        Event(string reporter, std::string name, std::string team_a_name, std::string team_b_name, int time,
              std::map<std::string, std::string> game_updates, std::map<std::string, std::string> team_a_updates,
              std::map<std::string, std::string> team_b_updates, std::string description);
        Event(const string & frame_body);
        virtual ~Event();
        const string & get_reporter() const;
        const string &get_team_a_name() const;
        const string &get_team_b_name() const;
        const string &get_name() const;
        int get_time() const;
        const map<string, string> &get_game_updates() const;
        const map<string, string> &get_team_a_updates() const;
        const map<string, string> &get_team_b_updates() const;
        const string &get_description() const;
        string extractFrameBody();

        string toJson();
};

// an object that holds the names of the teams and a vector of events, to be returned by the parseEventsFile function
struct names_and_events {
    string team_a_name;
    string team_b_name;
    vector<Event> events;
};

// function that parses the json file and returns a names_and_events object
names_and_events parseEventsFile(string json_path);
