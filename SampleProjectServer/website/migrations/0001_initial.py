# Generated by Django 2.1.1 on 2019-01-24 12:55

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='DataRecived',
            fields=[
                ('Id', models.AutoField(primary_key=True, serialize=False)),
                ('ImageSavedUrl', models.TextField()),
                ('PayerName', models.TextField(max_length=30, null=True)),
                ('ToUserName', models.TextField(max_length=30, null=True)),
                ('Create_date', models.DateTimeField(default=datetime.datetime(2019, 1, 24, 12, 55, 48, 687183))),
            ],
        ),
    ]
