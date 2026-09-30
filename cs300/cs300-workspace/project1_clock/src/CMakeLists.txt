# Build the Clock class as a reusable library.
add_library(project1_clock
    Clock.cpp
)

# Allow other targets to include Clock.h.
target_include_directories(project1_clock
    PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}
)

# Build the main Clock application.
add_executable(project1_app
    main.cpp
)

# Link the application with the Clock library.
target_link_libraries(project1_app
    PRIVATE project1_clock
)
