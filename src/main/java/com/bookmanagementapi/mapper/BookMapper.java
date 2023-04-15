package com.bookmanagementapi.mapper;

import com.bookmanagementapi.domain.Book;
import com.bookmanagementapi.dto.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final ModelMapper modelMapper;

    public BookMapper() {
        this.modelMapper = new ModelMapper();
    }

    public BookDto toDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public Book toEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }
}
