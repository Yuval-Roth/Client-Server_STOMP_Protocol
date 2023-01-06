#pragma once

#include "Frame.h"


class ConnectedFrame : public Frame {
    public:
        ConnectedFrame(unordered_map<string, string> headers, string frameBody);
};