include(GoogleTest)

add_executable(project2_tests
    project2_test.cpp
)

target_link_libraries(project2_tests
    PRIVATE
    project2_cart
    GTest::gtest_main
)

gtest_discover_tests(project2_tests)
