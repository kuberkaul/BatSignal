# settings.py
from django.conf import settings

settings.configure(
    DATABASE_ENGINE    = "django.db.backends.mysql",
    DATABASE_NAME      = "mydb",
    DATABASE_USER      = "mydb",
    DATABASE_PASSWORD  = "",
    DATABASE_HOST      = "mydb.cqxzi0jagmm9.us-east-1.rds.amazonaws.com",
    DATABASE_PORT      = "3306",
    INSTALLED_APPS = ('myApp',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.sites',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'django_twilio',
)
)

from django.db import models
from myApp.models import *
