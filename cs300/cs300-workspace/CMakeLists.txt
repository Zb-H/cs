# CS300 course workspace
#
# One CMake configure covers every assignment for the quarter. This file:
#   1. Sets the C++ standard used throughout the course.
#   2. Downloads GoogleTest once (via FetchContent), shared by every project.
#   3. Lists every project's subdirectory.
#
# Adding a new assignment later is two steps: create <projectN-name>/ with
# its own CMakeLists.txt (copy an existing project's as a starting point),
# then add one line for it below.
#
# Because every project shares this one configure, target names live in a
# single flat namespace across the whole workspace -- prefix every target
# with its project directory's number, e.g. project0_song, project1_app.
# That also means any project can target_link_libraries() against a target
# defined in an earlier project.

cmake_minimum_required(VERSION 3.16)
project(CS300Workspace LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

include(FetchContent)
FetchContent_Declare(
  googletest
  GIT_REPOSITORY https://github.com/google/googletest.git
  GIT_TAG        v1.15.2
  GIT_SHALLOW    TRUE
)
# Windows note: keeps GoogleTest's runtime library setting consistent with
# the rest of the workspace so linking doesn't fail on MSVC.
set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
FetchContent_MakeAvailable(googletest)

enable_testing()

add_subdirectory(project0-playlist-warmup)
