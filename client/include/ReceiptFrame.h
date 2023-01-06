#pragma once

#include "Frame.h"

class ReceiptFrame : public Frame {
    public:
        ReceiptFrame(unordered_map<string, string> headers, string frameBody);
};