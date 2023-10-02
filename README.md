# envers

- This project is made for auditing with hibernate envers
- spring.jpa.properties.org.hibernate.envers.store_data_at_delete=true used for keep data after delete operation.
  By this way we can audit deleted columns ass well
- bulkSave endpoint created for measure the differences between using envers or not.
- hibernate envers works as sycnhronous. Therefore it has some performance issue on source database.
- for simple and small size of data collection envers can be implemented for auditing
- but if you have large amount of data and need to audit data you should choose asynchronous ways to do it.
