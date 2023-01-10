#include "SendFrame.h"
#include "GameEvent.h"

SendFrame::SendFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}

SendFrame *SendFrame::get(string messageToSend)
{
    return nullptr;
}
SendFrame *SendFrame::get(GameEvent& gameEvent)
{
    return nullptr;
}