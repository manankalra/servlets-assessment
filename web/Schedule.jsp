<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Schedule</title>
</head>
<body style="background-color: darkseagreen;">
<center>
    <h2>Servlets-Assessment</h2><hr /><br /><br />
    <form action="/Schedule" method="post">
        <b>Class Name:</b> <input type="text" name="title" size="35"><br /><br />
        <b>Days:</b>
        Mon<input type="checkbox" name="day" value="mon"/>
        Tue<input type="checkbox" name="day" value="tue"/>
        Wed<input type="checkbox" name="day" value="wed"/>
        Thu<input type="checkbox" name="day" value="thu"/>
        Fri<input type="checkbox" name="day" value="fri"/>
        Sat<input type="checkbox" name="day" value="sat"/>
        <br /><br />
        <b>Time:</b>
        <select name="starttime">
            <option value="8">8:00am</option>
            <option value="9">9:00am</option>
            <option value="10">10:00am</option>
            <option value="11">11:00am</option>
            <option value="12">12:00pm</option>
            <option value="13">1:00pm</option>
            <option value="14">2:00pm</option>
            <option value="15">3:00pm</option>
            <option value="16">4:00pm</option>
            <option value="17">5:00pm</option>
        </select>
        to
        <select name="endtime">
            <option value="9">9:00am</option>
            <option value="10">10:00am</option>
            <option value="11">11:00am</option>
            <option value="12">12:00pm</option>
            <option value="13">1:00pm</option>
            <option value="14">2:00pm</option>
            <option value="15">3:00pm</option>
            <option value="16">4:00pm</option>
            <option value="17">5:00pm</option>
            <option value="18">6:00pm</option>
        </select>
        <br /><br />
        <b>Trainer:</b>
        <select name="trainerName">
            <option value="Trainer 1">Trainer 1</option>
            <option value="Trainer 2">Trainer 2</option>
            <option value="Trainer 3">Trainer 3</option>
            <option value="Trainer 4">Trainer 4</option>
            <option value="Trainer 5">Trainer 5</option>
        </select>
        <select name="subjectOne">
            <option value="hist">History</option>
            <option value="psycho">Psychology</option>
            <option value="perio">Periodontics</option>
            <option value="epist">Epistemology</option>
        </select>
        <select name="subjectTwo">
            <option value="pattern">Pattern Recognition</option>
            <option value="probab">Probabilistic Models</option>
            <option value="timeseries">Time-Series Analysis</option>
            <option value="blockchain">Blockchain for Healthcare</option>
        </select>
        <br /><br />
        <input type="submit" name="Submit" value="Add Slot">
    </form>
    <br /><div id="warning" name="warning" style="color:red"></div><br />
    <table border="1" cellspacing="0">>
        <tbody>
        <tr>
            <th align="center" valign="middle" width="80"></th>
            <th align="center" valign="middle">Monday</th>
            <th align="center" valign="middle">Tuesday</th>
            <th align="center" valign="middle">Wednesday</th>
            <th align="center" valign="middle">Thursday</th>
            <th align="center" valign="middle">Friday</th>
            <th align="center" valign="middle">Saturday</th>
        </tr>
        <c:forEach begin="8" end="17" step="1" var="time">
            <tr>
                <td align="center" valign="middle" width="80">
                    <c:choose>
                        <c:when test="${time==12}">
                            <c:out value="${time}"/>:00pm
                        </c:when>
                        <c:when test="${time > 12}">
                            <c:out value="${time - 12}"/>:00pm
                        </c:when>
                        <c:otherwise>
                            <c:out value="${time}"/>:00am
                        </c:otherwise>
                    </c:choose>
                </td>
                <c:forEach begin="0" end="5" step="1" var="day">
                    <td align="center" valign="middle" width="100">
                        <c:forEach items="${schedule.periods}" var="period">
                            <c:if test="${period.startTime <= time && period.endTime > time && period.day == day}">
                                <c:out value="${period.title} (${trainerName})"/>
                            </c:if>
                        </c:forEach>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </center>
</body>
</html>
