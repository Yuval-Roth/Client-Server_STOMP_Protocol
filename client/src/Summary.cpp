#include "../include/Summary.h"

Summary::Summary(string userName) : userName(userName) {}

void Summary::addEvent(Event &event) {
    string eventString = event->get_name() + " " + to_string(event->get_time()) + " " + event->get_discription();
    gameEvents.push_back(eventString);

}

