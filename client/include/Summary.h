#ifndef CLIENT_SUMMARY_H
#define CLIENT_SUMMARY_H

using namespace std;

#include <string>
#include <map>
#include <list>

class Event;

class Summary {

    private:
        string userName;
        // name of team a
        string team_a_name;
        // name of team b
        string team_b_name;
        // map of all the general game updates
        map<string, string> general_stats;
        // map of all team a updates the second type can be a string bool or int
        map<string, string> team_a_stats;
        // map of all team b updates
        map<string, string> team_b_stats;
        // description of the event
        list<string> gameEvents;


    public:
        Summary(string userName);
        void addEvent(Event& event);
        void printSummary();
        ~Summary();
};


#endif //CLIENT_SUMMARY_H
