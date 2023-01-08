#include "ErrorFrame.h"

ErrorFrame::ErrorFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody)
{
}
