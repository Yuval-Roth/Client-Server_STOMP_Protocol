#ifndef CLIENT_SUMMARY_H
#define CLIENT_SUMMARY_H

using namespace std;

#include <string>
#include <map>
#include <list>

class Event;
struct gameEvent {
        int time;
        string name;
        string description;

        gameEvent(int time, string name, string description)
                : time(time), name(name), description(description) {}
};
class Summary {
// TODO: Override team stats
    private:
        string userName;
        string gameName;
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
        list<gameEvent> firstHalfEvents;
        list<gameEvent> secondHalfEvents;



    public:
        Summary(string userName, string gameName);
        void addEvent(Event& event);
        void printSummary();
        ~Summary();

    private:
        static void sortedEventInsert(list<gameEvent> eventList, gameEvent event);
};




#endif //CLIENT_SUMMARY_H
