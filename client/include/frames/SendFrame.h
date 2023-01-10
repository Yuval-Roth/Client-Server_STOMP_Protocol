#pragma once

#include "Frame.h"

class GameEvent;

class SendFrame : public Frame {
    public:
        SendFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        static SendFrame* get(string fileName);
        static SendFrame* get(GameEvent& gameEvent);
};