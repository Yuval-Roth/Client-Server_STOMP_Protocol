#pragma once

#include "Frame.h"

class ReceiptFrame : public Frame {
    public:
        ReceiptFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
};