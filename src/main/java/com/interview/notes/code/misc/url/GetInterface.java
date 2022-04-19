package com.interview.notes.code.misc.url;

public interface GetInterface {
    @Get("/group/user")
    void getUser(@Field("id") String id);

    @Get("/genre/book")
    void getBook(@Field("isbn") String isbn);
}