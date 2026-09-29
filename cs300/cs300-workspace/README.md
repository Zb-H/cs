# CS300 course workspace

One CMake configure covers every assignment for the quarter. Each assignment
is its own subdirectory, with its own `src/` and `tests/`:

```
.
├── CMakeLists.txt                  # top level: C++ standard, pulls in GoogleTest once,
│                                   # lists every project's subdirectory
└── project0-playlist-warmup/       # a working example -- confirms your setup is correct
    ├── CMakeLists.txt
    ├── src/
    │   ├── CMakeLists.txt          # builds project0_song (a library) and project0_app
    │   ├── song.h/.cpp
    │   ├── main.cpp
    │   ├── playlist.txt            # sample data read at run time
    │   └── stages/                 # lecture 1 demos (optional -- see below)
    │       ├── stage1_main.cpp
    │       ├── stage2_main.cpp
    │       └── stage3_main.cpp
    └── tests/
        ├── CMakeLists.txt          # builds project0_unit_tests, registers cases with CTest
        └── song_test.cpp
```

`stages/` holds the step-by-step versions of the program shown during lecture 1
(basic I/O, then arrays, then file I/O, then the full version in `main.cpp`).
They build as `project0_stage1_demo`, `project0_stage2_demo` and
`project0_stage3_demo` if you want to run or compare them, but the setup
handout only walks through the final version.

Every project you add later follows this same shape and gets one new line
in the top-level `CMakeLists.txt`. Because the whole workspace shares a
single configure, target names live in one flat namespace — prefix every
target with its project directory's number (`project1_...`, `project2_...`)
so names never collide, and so any later project can
`target_link_libraries()` against a target an earlier project defined.

## Build and run

```
cmake -S . -B build
cmake --build build
./build/project0-playlist-warmup/src/project0_app   # expected: prints all 10 songs, then the favorite
ctest --test-dir build                              # expected: 100% tests passed, 15 tests
```

The first `cmake -S . -B build` needs internet access once, to download
GoogleTest. After that, everything builds offline.

See the setup handout for the full walkthrough, including installing a
compiler and VS Code extensions.
