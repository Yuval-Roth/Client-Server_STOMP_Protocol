#include "../include/Summary.h"
#include "event.h"

Summary::Summary(string userName) : userName(userName) {}

void Summary::addEvent(Event &event) {
    gameEvent gameEvent1(event.get_time(), event.get_name(), event.get_description());
        if (event.get_time() < 45) {
                firstHalfEvents.push_back(gameEvent1);
        } else {
                secondHalfEvents.push_back(gameEvent1);
        }

}

