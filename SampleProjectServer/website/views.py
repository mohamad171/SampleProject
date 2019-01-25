from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader
from django.views.decorators.csrf import csrf_exempt
from pymongo import MongoClient
from bson import json_util
import requests
from django.shortcuts import redirect
import json
from django.contrib.auth import authenticate , login , logout
from django.contrib.auth.models import User
from django.contrib.auth.decorators import login_required
from website import forms
from website import models




def main(request):
    return redirect("login")

def LogOut(request):
    logout(request)
    return redirect("login")


def Login(request):

    if request.user.is_authenticated:
            return redirect("main")
    else:
        if request.method == "POST":
                user = authenticate(username=request.POST.get("email"),password=request.POST.get("password"))
                if user is not None:
                    login(request,user)
                    return HttpResponse(json.dumps({"status": "ok"}))
                else:
                    return HttpResponse(json.dumps({"status": "incorrect"}))
        else:
            return render(request, "login.html")


@login_required(login_url="login")
@csrf_exempt
def Index(request):
        return render(request,"index.html")


@csrf_exempt
def withDocument(request):
    if request.method == "POST":
        form = forms.ImageUpload(request.POST, request.FILES)
        if form.is_valid():
            datarecive = models.DataRecived()
            datarecive.ImageFile = form.cleaned_data['image']
            datarecive.PayerName = request.POST.get("payerName")
            datarecive.ToUserName = request.POST.get("fromName")
            datarecive.save()

    return HttpResponse(json.dumps({"status":"ok"}))
@csrf_exempt
def withoutDocument(request):
    if request.method == "POST":
        form = forms.ImageUpload(request.POST, request.FILES)
        if form.is_valid():
            datarecive = models.DataRecived()
            datarecive.ImageFile = form.cleaned_data['image']
            datarecive.save()

    return HttpResponse(json.dumps({"status":"ok"}))

def CreateUser():

    user = User.objects.create_user("mohamad_171","mohamadmohamadi249@gmail.com","09382138446")

def CreateSuperUser():

    user = User.objects.create_superuser("admin","mohamadmohamadi249@gmail.com","09382138446")

