<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<div class="container" ">
    <h1 class="text-center text-info mt-1">THỐNG KÊ HỆ THỐNG</h1>

    <div class="row">
        <div class="col-md-5 col-12">
            <h2>Số lượng người dùng</h2>
            <p>Giảng viên: ${totalTeachers}</p>
            <p>Sinh viên: ${totalStudents}</p>
        </div>
        <div class="col-md-7 col-12">
            <canvas id="userChart"></canvas>
        </div>
    </div>

    <div class="row">
        <div class="col-md-5 col-12">
            <h2>Thống kê đề cương theo môn học</h2>
            <ul>
                <c:forEach var="row" items="${specsBySubject}">
                    <li>${row[0]}: ${row[1]}</li>
                    </c:forEach>
            </ul>
        </div>
        <div class="col-md-7 col-12">
            <canvas id="subjectChart"></canvas>
        </div>
    </div>

    <div class="row">
        <div class="col-md-5 col-12">
            <h2>Thống kê đề cương theo trạng thái</h2>
            <ul>
                <c:forEach var="row" items="${specsByStatus}">
                    <li>${row[0]}: ${row[1]}</li>
                    </c:forEach>
            </ul>
        </div>
        <div class="col-md-7 col-12">
            <canvas id="statusChart"></canvas>
        </div>
    </div>

    <div class="row">
        <div class="col-md-5 col-12">
            <h2>Thống kê bình luận</h2>
            <ul>
                <c:forEach var="row" items="${commentsBySpec}">
                    <li>${row[0]}: ${row[1]}</li>
                    </c:forEach>
            </ul>
        </div>
        <div class="col-md-7 col-12">
            <canvas id="commentChart"></canvas>
        </div>
    </div>

    <div class="row">
        <div class="col-md-5 col-12">
            <h2>Thống kê điểm trung bình</h2>
            <ul>
                <c:forEach var="row" items="${avgScoresBySpec}">
                    <li>${row[0]}: ${row[1]}</li>
                    </c:forEach>
            </ul>
        </div>
        <div class="col-md-7 col-12">
            <canvas id="scoreChart"></canvas>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    function drawChart(ctx, labels, data, chartTitle) {
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: chartTitle,
                    data: data,
                    borderWidth: 1,
                    backgroundColor: ['rgba(75, 192, 192, 0.2)']
                }],
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    window.onload = function () {
        let ctxUser = document.getElementById("userChart");
        let ctxSubject = document.getElementById("subjectChart");
        let ctxStatus = document.getElementById("statusChart");
        let ctxComment = document.getElementById("commentChart");
        let ctxScore = document.getElementById("scoreChart");

        let userLabels = ['Giảng viên', 'Sinh viên'];
        let userData = [${totalTeachers}, ${totalStudents}];
        
        let subjectLabels = [];
        let subjectData = [];
        <c:forEach var="row" items="${specsBySubject}">
            subjectLabels.push('${row[0]}');
            subjectData.push(${row[1]});
        </c:forEach>
        
        let statusLabels = [];
        let statusData = [];
        <c:forEach var="row" items="${specsByStatus}">
            statusLabels.push('${row[0]}');
            statusData.push(${row[1]});
        </c:forEach>
        
        let commentLabels = [];
        let commentData = [];
        <c:forEach var="row" items="${commentsBySpec}">
            commentLabels.push('${row[0]}');
            commentData.push(${row[1]});
        </c:forEach>
        
        let scoreLabels = [];
        let scoreData = [];
        <c:forEach var="row" items="${avgScoresBySpec}">
            scoreLabels.push('${row[0]}');
            scoreData.push(${row[1]});
        </c:forEach>

        drawChart(ctxUser, userLabels, userData, 'Số lượng người dùng');
        drawChart(ctxSubject, subjectLabels, subjectData, 'Đề cương theo môn học');
        drawChart(ctxStatus, statusLabels, statusData, 'Đề cương theo trạng thái');
        drawChart(ctxComment, commentLabels, commentData, 'Bình luận theo đề cương');
        drawChart(ctxScore, scoreLabels, scoreData, 'Điểm trung bình theo đề cương');
    }
</script>











