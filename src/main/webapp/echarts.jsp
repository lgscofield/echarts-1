<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%

if (request.getUserPrincipal() == null) {
	response.sendRedirect("/echarts/echarts_security_check");
}
%>
<!DOCTYPE HTML>
<!-- Copyright 2010 Ian Hilt                                                -->
<!-- Licensed under the Apache License, Version 2.0 (the "License"); you    -->
<!-- may not use this file except in compliance with the License. You may   -->
<!-- may obtain a copy of the License at                                    -->
<!--                                                                        -->
<!-- http://www.apache.org/licenses/LICENSE-2.0                             -->
<!--                                                                        -->
<!-- Unless required by applicable law or agreed to in writing, software    -->
<!-- distributed under the License is distributed on an "AS IS" BASIS,      -->
<!-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or        -->
<!-- implied. License for the specific language governing permissions and   -->
<!-- limitations under the License.                                         -->
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>ECharts</title>
</head>
<body>
<div id="page-loading-message" style="font-size:15px; margin:20px;">Loading ECharts ...</div>
<script type="text/javascript" language="javascript"
	src="echarts/echarts.nocache.js"></script>
<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
	style="position: absolute; width: 0; height: 0; border: 0"></iframe>
</body>
</html>
