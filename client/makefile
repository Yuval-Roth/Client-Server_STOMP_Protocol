all: clean $(TARGET)

# Target executable name
TARGET = bin/StompWCIClient

# Source code files
SOURCES = $(wildcard src/*.cpp)

# Object files that will be created from the source code files
OBJECTS = $(SOURCES:src/%.cpp=bin/%.o)

# C++ compiler to use
CXX = g++

# C++ compiler flags
CXXFLAGS = -g -Wall -Weffc++ -std=c++11 -Iinclude

# Libraries to link to
LIBS = -lboost_system -lpthread

# Target to build
$(TARGET): $(OBJECTS)
	$(CXX) -o $(TARGET) $(OBJECTS) $(LIBS)

# Rule to build the object files from the source code files
bin/%.o: src/%.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@

# Rule to clean up the build artifacts
clean:
	rm -f $(OBJECTS) $(TARGET)
