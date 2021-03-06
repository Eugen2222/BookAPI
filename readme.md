
## 1. API 說明

	本API Service於docker環境啟動方式:
	1. 開啟powershell
	2. cd 至專案Dockerfile之資料夾
	3. 執行 "docker build -t bookapi ." 
	4. 執行 "docker run -p 8080:8080 -t bookapi"
	
	JUnit 請至src/test中參考。

## 1.1 列出H2中所有的書本API

	API類型：Restful WebService
	CONTENT-TYPE：application/json
## 1.1.1	請求規格
	環境別	類型	API路徑
	UT	Get	http://{host-ip}:8080/find
## 1.1.2	回應規格範例
	Body回應範例
	[
	    {
	        "id": 1,
	        "name": "Taipei",
	        "author": "James",
	        "publisher": "Taiwan good",
	        "publishDate": "1995-04-30",
	        "price": 599,
	        "isbn": null
	    },
	    {
	        "id": 2,
	        "name": "Taipei",
	        "author": "James",
	        "publisher": "Taiwan good",
	        "publishDate": "1995-04-30",
	        "price": 599,
	        "isbn": 12345
	    }
	]

## 1.2 新增一本書籍API

	API類型：Restful WebService
	CONTENT-TYPE：application/json
	
## 1.2.1 請求規格
	環境別	類型	API路徑
	UT	Post	http://{host-ip}:8080/insert

Body參數:

<table>
  <tr><td>欄位名稱</td><td>資料型態</td><td>最大長度</td><td>欄位說明</td></tr>
  <tr><td>name</td><td>String</td><td>500</td><td>名稱，不可為空值</td></tr>
  <tr><td>author</td><td>String</td><td>255</td><td>作者，不可為空值</td></tr>
  <tr><td>isbn</td><td>Integer</td><td></td><td></td></tr>
  <tr><td>publisher</td><td>String</td><td>255</td><td>出版商，不可為空值</td></tr>
  <tr><td>publishdate</td><td>String</td><td>10</td><td>出版日期，不可為空值，日期格式:yyyy-mm-dd (如:1994-05-06)</td></tr>
  <tr><td>price</td><td>Integer</td><td></td><td>定價，不可為空值</td></tr>
</table>

Body請求範例:

	{
	    "name":"Taipei",
	    "author":"James",
	    "isbn":12345,
	    "publisher":"Taiwan good",
	    "publishDate":"1995-04-30",
	    "price":599
	}


## 1.2.2	回應規格範例		
	{
	    "id": 2,
	    "name": "Taipei",
	    "author": "James",
	    "publisher": "Taiwan good",
	    "publishDate": "1995-04-30T00:00:00.000+00:00",
	    "price": 599,
	    "isbn": 12345
	}
	
## 1.3 更新一本書籍資訊API

	API類型：Restful WebService
	CONTENT-TYPE：application/json
	
## 1.3.1 請求規格
	環境別	類型	API路徑
	UT	put	http://{host-ip}:8080/update
Body參數:

<table>
  <tr><td>欄位名稱</td><td>資料型態</td><td>最大長度</td><td>欄位說明</td></tr>
  <tr><td>id</td><td>Integer</td><td></td><td>Id，不可為空值</td></tr>
  <tr><td>name</td><td>String</td><td>500</td><td>名稱，不可為空值</td></tr>
  <tr><td>author</td><td>String</td><td>255</td><td>作者，不可為空值</td></tr>
  <tr><td>isbn</td><td>Integer</td><td></td><td>不可為空值</td></tr>
  <tr><td>publisher</td><td>String</td><td>255</td><td>出版商，不可為空值</td></tr>
  <tr><td>publishdate</td><td>String</td><td>10</td><td>出版日期，不可為空值，日期格式:yyyy-mm-dd (如:1994-05-06)</td></tr>
  <tr><td>price</td><td>Integer</td><td></td><td>定價，不可為空值</td></tr>
</table>

Body請求範例:

	{
	    "id":1,
	    "name":"Taipei",
	    "author":"James",
	    "isbn":12345,
	    "publisher":"Taiwan good",
	    "publishDate":"1995-04-30",
	    "price":599
	}


## 1.3.2	回應規格範例		

	Succeeded. The information of Book id:1 had been updated. 
	
## 1.4 刪除一本書籍API

	API類型：Restful WebService
	CONTENT-TYPE：application/json
	
## 1.4.1 請求規格
	環境別	類型	API路徑
	UT	delete	http://{host-ip}:8080/delete/{id}

URL請求範例:
	
	http://localhost:8080/delete/3


## 1.4.2	回應規格範例		

	Succeeded. Book :3 had been deleted. 
	