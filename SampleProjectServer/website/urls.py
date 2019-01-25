from django.urls import path

from . import views

urlpatterns = [
    path('signin',views.Login,name='login'),
    path('signout',views.LogOut,name='logout'),
    path('panel/index',views.Index,name='main'),
    path('withDocument',views.withDocument,name="withDocument"),
    path('withoutDocument',views.withoutDocument,name="withoutDocument"),
    path('',views.main,name='index'),


]