# tdd-practice-stupid-example
As a system user, I’d like to be able to configure alerts (for my company) in order to monitor campaigns behavior.

Alert configuration
Create alert configuration screen
Table with existent alert configurations.  Client side paging.
Operations - add, delete, update alert configurations

Alert configuration structure
1. Name
2. company id - configuration should be created per company.
3. Notification types: system notification (default, can’t be disabled), email, sms, slack (shown only for admin) 
4. List of conditions - similar to campaign views
  conditions for column. Columns can be textual and numeric.
    Textual columns can be filtered by equals, not equals, contains, in list (comma separated)
    Numeric columns can be filtered by more than, less than, equals, not equals, in list (comma separated)



http://localhost:8080/swagger-ui.html#/
POST /rest/api/v1/alerts/{companyId}/configurations
{
"name":"test",
   "company": {
    "id": 1
  },
  "notificationTypes": [
    {
      "name": "SYSTEM"
    }
  ],
  "conditions": [
    {
      "conditions": {
        "name": "EQUALS"
      },
      "field": {
        "name": "ID"
      },
      "fieldValues": [
        "1"
      ]
    }
  ]
}
