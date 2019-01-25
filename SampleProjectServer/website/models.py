from django.db import models
import django.utils
class DataRecived(models.Model):
    Id = models.AutoField(primary_key=True)
    ImageFile = models.ImageField(upload_to = 'pic_folder/',default = 'pic_folder/no-img.jpg')
    PayerName = models.TextField(null=True,max_length=30)
    ToUserName = models.TextField(null=True, max_length=30)
    Create_date =  models.DateTimeField(default=django.utils.timezone.now)

