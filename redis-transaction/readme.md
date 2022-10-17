# Redis Transaction

# Overview:
In this short tutorial, I would like to show Redis Transaction with Spring Boot.

# Redis Transaction:
A Database Transaction is a set of operations which is either executed successfully a single unit of work or the changes are discarded in case of issues.

Most of the redis commands can be grouped under get/set. All these commands are atomic by default. But when we need to execute a set of commands sequentially, then it is NOT guaranteed to be atomic. 
Redis provides a support for transaction through multi, exec and discard commands.

We first tell redis that we are going to run a set of operations by invoking multi command. Then we perform the operations (A, B and C) as usual as shown in the below picture.  Once done, we either call exec() if things are good or discard() to ignore the changes.

# Sample Application:
We are going to consider a simple Bank application in which Redis is the primary DB. We have set of accounts. The users can transfer money from 1 account to another.

Lets see how to implement the money transfer as Redis Transaction with Spring Boot.

# Redis Transaction – SessionCallBack:
Spring Data Redis provides the SessionCallBack interface which needs to be implemented when we need to execute multiple operations as a single transaction.

- MoneyTransfer is an implementation of SessionCallBack which contains the business logic for money transfer.
- It will receive account Ids and the amount to be transferred.

