#include "SendFrame.h"
#include "event.h"
#include <unordered_map>

SendFrame::SendFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}


SendFrame *SendFrame::get(Event event)
{
    // TODO: implement

    event.get_name();

    return nullptr;
}
