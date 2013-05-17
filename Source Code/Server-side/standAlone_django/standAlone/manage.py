#  scp -i Kuber1.pem standAlone/ ec2-54-234-191-210.compute-1.amazonaws.com

#!/usr/bin/env python
import os
import sys

sys.path.append('/home/ubuntu/standAlone/')
sys.path.append('/home/ubuntu')

if __name__ == "__main__":
    os.environ.setdefault("DJANGO_SETTINGS_MODULE", "standAlone.settings")

    from django.core.management import execute_from_command_line

    execute_from_command_line(sys.argv)

account_sid = ''
auth_token= ''
from twilio.rest import TwilioRestClient
from myApp import models
from django.db import models
from django.db.models import *
from myApp.models import *
import sys, time

print 'The app *batsignal* started working'
var=1
while var == 1:
    
    users_cellnumber=[]
    for users_cellnumber1 in users.objects.all().values_list("users_cellnumber"):
        asd = users_cellnumber1[0].encode('utf-8')
        users_cellnumber.append(asd)
    
    for usercellnumber_distict in users_cellnumber:
        q = []
        for q1 in wreckwatch.objects.all().values_list("x").filter(users_cellnumber_id = usercellnumber_distict).order_by('-time'):
            q.append(q1)
    
        m = []
        for m1 in wreckwatch.objects.all().values_list("y").filter(users_cellnumber_id = usercellnumber_distict).order_by('-time'):
            m.append(m1)
    
        n=[]
        for n1 in wreckwatch.objects.all().values_list("z").filter(users_cellnumber_id = usercellnumber_distict).order_by('-time'):
            n.append(n1)		
    
        a = []
        b = []	
    
        for b1 in battery.objects.all().values_list("level").filter(users_cellnumber_id = usercellnumber_distict).order_by('-time1'):
            c = int(str(b1[0]))
            b.append(c)	
            
        numerator = (q[0][0]*q[0][0] + m[0][0]*m[0][0] + n[0][0]*n[0][0])
        denominator = ( 9.8 * 9.8 )
        g_force = numerator/denominator
    
        if g_force >= 3.5:
            final_list1 =[]
	    print 'wreckwatch was processed'
            for cellnumber in contacts.objects.all().values_list("cellnumbers").filter(users_cellnumber_id = usercellnumber_distict):
                final_list1.append(cellnumber[0].encode('utf-8'))
            for final1 in final_list1:
                client = TwilioRestClient(account_sid, auth_token)
                call = client.calls.create(url="http://twimlets.com/message?Message%5B0%5D="+"Wreckwatch%20alert.%20There%20is%20a%20high%20chance%20that%20your%20friend%20might%20have%20met%20with%20an%20accident", 
                to=final1,  from_="+15305549648")
                message = client.sms.messages.create(to=cellnumber, from_="+15305549648",
                body='Alert from Wreckwatch - your friend is about to die. Please wish for his recovery')	
	    time.sleep(5)		
        if b[1] > 10:
	    if b[0] <= 10:
            	print 'batsignal was processed'
            	final_list = []
            	for cellnumber in contacts.objects.all().values_list("cellnumbers").filter(users_cellnumber_id = usercellnumber_distict):
            	    final_list.append(cellnumber[0].encode('utf-8'))
            	for final in final_list:
            	    account_sid = ''
            	    auth_token= ''
            	    client = TwilioRestClient(account_sid, auth_token)
            	    call = client.calls.create(url="http://twimlets.com/message?Message%5B0%5D="+"Wreckwatch%20alert.%20There%20is%20a%20high%20chance%20that%20your%20friend%20might%20have%20met%20with%20an%20accident", 
            	    to="+14088369260",
            	    from_="+15305549648")
            	    message = client.sms.messages.create(to=final, from_="+15305549648",
            	    body='Alert from Bat-Signal : Your friends battery just hit 10%. Please get in contact with him ASAP if you think you might need him')		       
	else:
            continue
    
    
