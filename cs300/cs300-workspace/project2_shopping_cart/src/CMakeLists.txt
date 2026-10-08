add_library(project2_cart
    ArrayShoppingCart.cpp
)

target_include_directories(project2_cart
    PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}
)

add_executable(project2_app
    main.cpp
)

target_link_libraries(project2_app
    PRIVATE project2_cart
)
