#include "ReceiptFrame.h"

ReceiptFrame::ReceiptFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody)
{
}
