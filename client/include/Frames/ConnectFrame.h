#pragma once

#include "Frame.h"

class ConnectFrame : public Frame {
    private:
        
    public:
        ConnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        static Frame* get(string host, string username, string password);
};