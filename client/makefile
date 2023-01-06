all: clean $(TARGET)

# Specify the target executable name
TARGET = bin/StompWCIClient

# Specify the source code files
SOURCES = $(wildcard src/*.cpp)

# Specify the object files that will be created from the source code files
OBJECTS = $(SOURCES:src/%.cpp=bin/%.o)

# Specify the C++ compiler to use
CXX = g++

# Specify the C++ compiler flags
CXXFLAGS = -g -Wall -Weffc++ -std=c++11 -Iinclude

# Specify the libraries to link to
LIBS = -lboost_system -lpthread

# Default target
$(TARGET): $(OBJECTS)
	$(CXX) $(OBJECTS) $(LIBS) -o $(TARGET)

# Rule to build the object files from the source code files

bin/%.o: src/%.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@

# Rule to clean up the build artifacts
.PHONY: clean

clean:
	rm -f $(OBJECTS) $(TARGET)
