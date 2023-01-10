#include "UnsubscribeFrame.h"

const string ID_HEADER = "id";
const string DESTINATION_HEADER = "destination";

UnsubscribeFrame::UnsubscribeFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}

UnsubscribeFrame *UnsubscribeFrame::get(int subId, string topic)
{
    unordered_map<string, string> headers;
    headers[ID_HEADER] = to_string(subId);
    headers[DESTINATION_HEADER] = topic;
    return new UnsubscribeFrame(UNSUBSCRIBE, headers, "");
}
