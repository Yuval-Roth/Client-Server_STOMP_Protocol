#include "SendFrame.h"
#include "event.h"
#include <unordered_map>
#include "UserData.h"

SendFrame::SendFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}


SendFrame *SendFrame::get(Event& event)
{
    UserData &userData = UserData::getInstance();
    unordered_map<string, string> headers;
    headers["destination"] = "/" + event.get_team_a_name() + "_" + event.get_team_b_name();
    string body = event.extractFrameBody();
    SendFrame *frame = new SendFrame(StompCommand::SEND, headers, body);
    return frame;
}
