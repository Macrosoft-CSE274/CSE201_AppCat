# -*- coding: utf-8 -*-
# Generated by Django 1.11.7 on 2017-11-25 01:46
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0002_applicationentry'),
    ]

    operations = [
        migrations.CreateModel(
            name='ApplicationAddRequest',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(default='default', max_length=30)),
                ('approved', models.BooleanField(default=False)),
                ('developer', models.CharField(default='default', max_length=30)),
                ('platform', models.CharField(default='default', max_length=30)),
                ('versions', models.CharField(default='default', max_length=30)),
                ('price', models.FloatField()),
                ('feedback', models.CharField(default='I am sorry that your application is not approved.', max_length=300)),
            ],
        ),
        migrations.CreateModel(
            name='Comment',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('authorName', models.CharField(default='default', max_length=100)),
                ('content', models.CharField(default='This app seems good. no error detected.', max_length=300)),
                ('appName', models.CharField(default='', max_length=30)),
            ],
        ),
    ]