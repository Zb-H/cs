include(GoogleTest)

add_executable(project0_unit_tests song_test.cpp)
target_link_libraries(project0_unit_tests PRIVATE project0_song GTest::gtest_main)

# Automatically discovers each TEST(...) case and registers it with CTest
# (and therefore with VS Code's Test Explorer) individually.
gtest_discover_tests(project0_unit_tests)
