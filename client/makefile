TARGET = bin/StompWCIClient

# Source code files
#$(wildcard src/*.cpp)
SOURCES = $(shell find src -name '*.cpp')

# Object files that will be created from the source code files
OBJECTS = $(SOURCES:src/%.cpp=bin/%.o)

# C++ compiler to use
CXX = g++

# C++ compiler flags
CXXFLAGS = -g -Wall -Weffc++ -std=c++11 -Iinclude -Iinclude/frames

# Libraries to link to
LIBS = -lboost_system -lpthread

# .PRECIOUS: createBin

# Default target to build
$(TARGET): $(OBJECTS)
	$(CXX) -o $(TARGET) $(OBJECTS) $(LIBS)

# Rule to build the object files from the source code files
bin/%.o: src/%.cpp #createBin
	$(CXX) $(CXXFLAGS) -c $< -o $@

default: createBin $(TARGET)

createBin:
	mkdir -p bin
	mkdir -p bin/frames

all: clean $(TARGET)

# delta: bin/%.cpp
# 	binDelta/%.o

# binDelta/%.o: src/%.cpp
# 	$(CXX) $(CXXFLAGS) -c $< -o $@

# Rule to clean up the build artifacts
clean:
	rm -f -r bin/*.o bin/frames/*.o bin/StompWCIClient
