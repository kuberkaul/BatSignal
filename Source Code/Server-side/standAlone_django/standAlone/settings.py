DATABASES = {
    'default': {
	'ENGINE'    : "django.db.backends.mysql",
    	'NAME'      : "mydb",
    	'USER'      : "mydb",
    	'PASSWORD'  : "",
   	'HOST'      : "mydb.cqxzi0jagmm9.us-east-1.rds.amazonaws.com",
    	'PORT'      : "3306",
	}
}
#INSTALLED_APPS     = ("myApp")
INSTALLED_APPS = ('myApp',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.sites',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'django_twilio',
)
SECRET_KEY = '!%*$hwgg@l&6qi4bv)6n2bom&hr*8pt&)%y-)156j(q!ikf1y&'
account_sid = ''
auth_token= ''
TWILIO_DEFAULT_CALLERID = '9173381330'


