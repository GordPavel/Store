<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 10.12.18
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<script id="node" type="text/x-jsrender">
    <li class="Node ExpandLeaf IsLast">
                            <div class="Expand"></div>
                            <input type="checkbox"/>
                            <div class="Content">{{ >name }}</div>
    </li>


</script>
<meta charset="utf-8">
<script src="/js/brutusin-json-forms.js"></script>
<link rel="stylesheet" href='/css/brutusin-json-forms.min.css'/>
<script src="/js/brutusin-json-forms-bootstrap.min.js"></script>
<script src="/js/brutusin-json-forms.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/der'mo.css"/>
<script src="/js/onPage/admin.js"></script>
<style type="text/css"> ul.hr li {
    margin-left: 30px;
} </style>
<div class="ifelsediv">
    <label>Select an action </label>
    <select id="setAction">
        <option value="Delete">Delete</option>
        <option value="Change">Change</option>
        <option value="Add category">Add category</option>
        <option value="Add product">Add product</option>
    </select>
    <button id="apply">Apply selected option</button>
</div>
<div class="category"></div>
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <div id="addNewCat"></div>
        <input type="file" id="fileinput"/>
        <button id="addButtonCategory">Add element</button>
        <button id="addButtonProduct">Add product</button>
    </div>
</div>
