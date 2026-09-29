# This project's code lives in a small static library so both its "app"
# executable and its "unit_tests" executable (in ../tests) link against the
# exact same compiled code, instead of each getting its own copy.
#
# Target names are prefixed project0_ because every project in the
# workspace shares one flat CMake target namespace (see the root
# CMakeLists.txt) -- project1 will prefix its targets project1_, and so on.

add_library(project0_song STATIC
    song.cpp
    song.h
)

target_include_directories(project0_song PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

add_executable(project0_app main.cpp)
target_link_libraries(project0_app PRIVATE project0_song)

# playlist.txt (the sample data main.cpp reads at run time) lives next to
# the source, not next to the built executable -- and where the built
# executable ends up, and what the current directory is when it runs,
# both vary by how you launch it (VS Code's Run button, a terminal in the
# workspace root, ctest, ...). Baking in the absolute path to playlist.txt
# at compile time sidesteps all of that: main.cpp always finds it, no
# matter where or how project0_app is run from.
target_compile_definitions(project0_app PRIVATE
    PLAYLIST_DATA_DIR="${CMAKE_CURRENT_SOURCE_DIR}")

# ----------------------------------------------------------------------------
# Lecture 1 stage demos (optional -- not covered by the setup handout)
# ----------------------------------------------------------------------------
# These are the step-by-step versions of the program above, shown in lecture:
# stage 1 (basic I/O) -> stage 2 (arrays/loops) -> stage 3 (file I/O) -> the
# full struct-and-header version in main.cpp/song.h/song.cpp. Each one is its
# own small, self-contained .cpp file (not linked against project0_song), so
# they build and run independently if you want to compare them side by side.

add_executable(project0_stage1_demo stages/stage1_main.cpp)

add_executable(project0_stage2_demo stages/stage2_main.cpp)

add_executable(project0_stage3_demo stages/stage3_main.cpp)
target_compile_definitions(project0_stage3_demo PRIVATE
    PLAYLIST_DATA_DIR="${CMAKE_CURRENT_SOURCE_DIR}")
