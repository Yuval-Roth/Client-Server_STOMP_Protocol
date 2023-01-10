#pragma once

#include "ExecutableFrame.h"

class ConnectFrame : public Frame {
    private:
        
    public:
        ConnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        void execute(ConnectionHandler& handler);
        const string USERNAME_HEADER = "Username";
        const string PASSWORD_HEADER = "Password";
        const string ACCEPT_VERSION_HEADER = "accept-version";
        const string HOST_HEADER = "host";

        static Frame* get(string host, string port, string username, string password);
};