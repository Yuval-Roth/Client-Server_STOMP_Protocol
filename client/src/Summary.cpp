#include "../include/Summary.h"
#include "event.h"

Summary::Summary(string userName) : userName(userName) {}

void Summary::addEvent(Event &event) {
    gameEvent gameEvent1(event.getTime(), event.getName(), event.getDescription());

}

