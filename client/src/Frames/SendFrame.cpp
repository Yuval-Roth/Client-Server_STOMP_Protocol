#include "SendFrame.h"
#include "GameEvent.h"
#include "event.h"

SendFrame::SendFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}


SendFrame *SendFrame::get(Event event)
{
    // TODO: implement
    return nullptr;
}
