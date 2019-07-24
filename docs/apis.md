# REST APIs

## Book apis

### GET /api/v1/books/{bookId} (책 상세 정보)


```
Headers:
   - X-USER-AUTH: {AUTHENTICATION_TOKEN}

Response:
   data: Book
     - name: string
     - tumbnail: string
     - contents: string
     - isbn: string
     - authors: List<String>
     - publisher: string
     - publish_datetime: datetime
     - price: int
     - sale_price: int
   meta:
     - result_code: int
     - result_msg: string
```


### GET /api/v1/books (책 검색 결과 리스트)


```
Parameters:
   - target-type: "title", "person", "isbn", "publisher"
   - page: integer
   - query: string

Headers:
   - X-USER-AUTH: {AUTHENTICATION_TOKEN}

Response:
   data: BookList
     - books: List<Book>
         - book_id: long
         - name: string
         - thumbnail: string
         - authors: List<String>
         - price: int
         - sale_price: int
     - page: int
     - total: int
     - is_end: boolean
   meta:
     - result_code: int
     - result_msg: string
```


## Popular apis


### GET /api/v1/populars-keyword (책 검색 인기 차트)


```
Headers:
   - X-USER-AUTH: {AUTHENTICATION_TOKEN}

Response:
   data: List<Keyword>
     - query_string: string
     - count: long
   meta:
     - result_code: int
     - result_msg: string
```


### GET /api/v1/users/history (유저 검색 기록)


```
Headers:
   - X-USER-AUTH: {AUTHENTICATION_TOKEN}

Response:
   data: List<Keyword>
     - keyword: string
     - history_datetime: long
   meta:
     - result_code: int
     - result_msg: string
```


## User apis


### POST /api/v1/users/join (회원가입)


```
Request body:
    - id: string
    - password: string

Response:
    meta:
      - result_code: int (success: 201)
      - result_msg: string
```


### POST /api/v1/users/login (로그인)


```
    - id: string
    - password: string

Response:
    data:
      - token: string
    meta:
      - result_code: int (success: 201)
      - result_msg: string
```
