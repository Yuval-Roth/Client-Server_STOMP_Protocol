#include "ConnectFrame.h"

ConnectFrame::ConnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}


void ConnectFrame::execute(ConnectionHandler &handler)
{
}

Frame *ConnectFrame::get(string host, string port, string username, string password)
{
    unordered_map<string, string> headersMap;
    headersMap["accept-version"] = "1.2";
    headersMap["host"] = host;
    headersMap["username"] = username;
    headersMap["password"] = password;
    StompCommand command = StompCommand::CONNECT;
    string frameBody = "";
}

