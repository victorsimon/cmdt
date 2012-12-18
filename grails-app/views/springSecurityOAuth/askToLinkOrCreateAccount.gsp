<head>
    <meta name='layout' content='main'/>
    <style type="text/css">
    fieldset {
        border: 1px solid gray;
        padding: 1em;
        font: 80%/1 sans-serif;
    }

    fieldset legend {
        padding: 0.2em 0.5em;
        border: 1px solid gray;
        color: green;
        font-weight: bold;
        font-size: 90%;
        text-align: right;
    }

    fieldset label {
        float: left;
        width: 25%;
        margin-top: 5px;
        margin-right: 0.5em;
        padding-top: 0.2em;
        text-align: right;
        font-weight: bold;
    }

    fieldset input[type="submit"] {
        float: right;
        background: #F0F0F0;
        cursor: pointer;
    }

    fieldset br {
        margin-top: 10px;
    }
    </style>
</head>

<body>

<div class='body' >
    <g:if test='${flash.error}'>
        <div class="errors">${flash.error}</div>
    </g:if>

    <h4><g:message code="springSecurity.oauth.registration.link.not.exists" default="No he encontrado un usuario para esta cuente." args="[session.springSecurityOAuthToken.providerName]"/></h4>
    <br/>

    <g:hasErrors bean="${createAccountCommand}">
    <div class="errors">
        <g:renderErrors bean="${createAccountCommand}" as="list"/>
    </div>
    </g:hasErrors>

    <g:form action="createAccount" method="post" autocomplete="off">
        <fieldset>
            <legend><g:message code="springSecurity.oauth.registration.create.legend" default="Crear una nueva"/></legend>
            <div class="fieldcontain ${hasErrors(bean: createAccountCommand, field: 'username', 'error')} ">
                <label for='username'><g:message code="OAuthCreateAccountCommand.username.label" default="Usuario"/>:</label>
                <g:textField name='username' value='${createAccountCommand?.username}'/>
            </div>
            <div class="fieldcontain ${hasErrors(bean: createAccountCommand, field: 'password1', 'error')} ">
                <label for='password1'><g:message code="OAuthCreateAccountCommand.password1.label" default="Clave"/>:</label>
                <g:passwordField name='password1' value='${createAccountCommand?.password1}'/>
            </div>
            <div class="fieldcontain ${hasErrors(bean: createAccountCommand, field: 'password2', 'error')} ">
                <label for='password2'><g:message code="OAuthCreateAccountCommand.password2.label" default="Re-introduzca la clave"/>:</label>
                <g:passwordField name='password2' value='${createAccountCommand?.password2}'/>
            </div>
            <g:submitButton name="${message(code: 'springSecurity.oauth.registration.create.button', default: 'Create')}"/>
        </fieldset>
    </g:form>

    <g:form action="linkAccount" method="post" autocomplete="off">
        <fieldset>
            <legend><g:message code="springSecurity.oauth.registration.link.legend" default="Enlazar con una existente"/></legend>
            <div class="fieldcontain ${hasErrors(bean: linkAccountCommand, field: 'username', 'error')} ">
                <label for='username'><g:message code="OAuthLinkAccountCommand.username.label" default="Usuario"/>:</label>
                <g:textField name='username' value='${createAccountCommand?.username}'/>
            </div>
            <div class="fieldcontain ${hasErrors(bean: linkAccountCommand, field: 'password', 'error')} ">
                <label for='password1'><g:message code="OAuthLinkAccountCommand.password.label" default="Clave"/>:</label>
                <g:passwordField name='password' value='${linkAccountCommand?.password}'/>
            </div>
            <g:submitButton name="${message(code: 'springSecurity.oauth.registration.link.button', default: 'Link')}"/>
        </fieldset>
    </g:form>

    <g:link controller="login" action="auth"><g:message code="springSecurity.oauth.registration.back" default="Volver"/></g:link>
</div>

</body>
