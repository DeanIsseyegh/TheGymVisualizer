package models

import play.api.libs.json.Json

case class UserData(totalNumSessions: Long,
    averageNumSessionsPerWeek: Double,
    averageNumSessionsPerMonth: Double,
    averageSessionLengthMins: Double,
    totalSessionLengthMins: Double,
    daysSinceFirstAndLastSession: Long,
    daysSinceFirstSessionAndNow: Long)
    
 object UserData {
  implicit val userDataForm = Json.format[UserData]
}
    
    
    
    
  /*  long getTotalNumOfSessions();
  double getAverageNumOfSessionsPerWeek();
  double getAverageNumOfSessionsPerMonth();
  double getAverageSessionLengthMins();
  double getTotalSessionLengthMins();
  long getDaysSinceFirstAndLastSession();
  long getDaysSinceFirstSessionAndNow();
  Period getPeriodBetweenFirstAndLastSession();
  Period getPeriodBetweenFirstSessionAndNow();
  LocalDate getFirstSessionDate();
  LocalDate getLastSessionDate();*/