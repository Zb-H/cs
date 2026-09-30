# Enable GoogleTest test discovery.
include(GoogleTest)

# Build the unit-test executable.
add_executable(project1_tests
    ClockTest.cpp
)

# Link the tests to the Clock library and GoogleTest.
target_link_libraries(project1_tests
    PRIVATE
    project1_clock
    GTest::gtest_main
)

# Register each GoogleTest test with CTest.
gtest_discover_tests(project1_tests)
