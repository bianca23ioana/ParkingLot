<%@page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add car photo">
  <h1>Add Car Photo</h1>
  <form class="needs-validation" novalidate method="POST" enctype="multipart/form-data"
        action="${pageContext.request.contextPath}/AddCarPhoto">
    <div class="row">
      <div class="col-md-6 mb-3">
        License plate: ${car.licensePlate}
      </div>
    </div>
    <div class="row">
      <div class="col-md-6 mb-3">
        <input type="file" name="file" id="file" required>
        <div class="invalid-feedback">
          Photo is required.
        </div>
      </div>
    </div>
    <input type="hidden" name="car_id" value="${car.id}"/>
    <hr class="mb-4">
    <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
  </form>
</t:pageTemplate>