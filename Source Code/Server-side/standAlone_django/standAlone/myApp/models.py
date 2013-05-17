# myApp/models.py
from django.db import models
from django.core.validators import MaxValueValidator, MinValueValidator

class MinMaxFloat(models.FloatField):
    def __init__(self, min_value=None, max_value=None, *args, **kwargs):
        self.min_value, self.max_value = min_value, max_value
        super(MinMaxFloat, self).__init__(*args, **kwargs)

    def formfield(self, **kwargs):
        defaults = {'min_value': self.min_value, 'max_value' : self.max_value}
        defaults.update(kwargs)
        return super(MinMaxFloat, self).formfield(**defaults)

class users(models.Model):
	id = models.AutoField(primary_key=True)
	users_cellnumber=models.CharField(max_length=10, unique=True)

class wreckwatch(models.Model):
	#cellnumbers=models.IntergerField(default=10, max_length=10)
	#permission=models.CharField(max_length=128)
	time=models.DateTimeField()
	users_cellnumber=models.ForeignKey(users, to_field = 'users_cellnumber')
	def save(self):
		self.uploaded_time=datetime.now()
		models.Model.save(self)
	x = models.FloatField(
    validators = [MinValueValidator(-100.00), MaxValueValidator(1000.00)])
	y = models.FloatField(
    validators = [MinValueValidator(-100.00), MaxValueValidator(1000.00)])
	z = models.FloatField(
    validators = [MinValueValidator(-100.00), MaxValueValidator(1000.00)])

class contacts(models.Model):
	cellnumbers=models.CharField(max_length=10)
	users_cellnumber=models.ForeignKey(users, to_field = 'users_cellnumber')

class battery(models.Model):
	level = models.IntegerField(default=3, max_length=3)
	time1 = models.DateTimeField()
	users_cellnumber=models.ForeignKey(users, to_field = 'users_cellnumber')
	def save(self):
		self.uploaded_time1=datetime.now()
		models.Model.save(self)
	




