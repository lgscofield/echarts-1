USE [EW-EHR]
GO
/****** Object:  User [echarts]    Script Date: 04/09/2010 12:39:31 ******/
CREATE USER [echarts] FOR LOGIN [echarts] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Schema [form]    Script Date: 04/09/2010 12:39:31 ******/
CREATE SCHEMA [form] AUTHORIZATION [dbo]
GO
/****** Object:  Table [dbo].[Demographics]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Demographics](
	[Demographics_id] [bigint] IDENTITY(1,1) NOT NULL,
	[Patient_Id] [bigint] NULL,
	[Insurance] [varchar](20) NULL,
	[Gender] [varchar](1) NULL,
	[DOB] [datetime] NULL,
	[Race] [varchar](1) NULL,
	[Veteran] [bit] NULL,
	[Religion] [varchar](50) NULL,
	[MaritalStatus] [varchar](1) NULL,
	[EducationLevel] [varchar](2) NULL,
	[EducationType] [varchar](1) NULL,
	[LivingArrangement] [varchar](1) NULL,
	[Employment] [varchar](1) NULL,
	[IncomeSource1] [varchar](1) NULL,
	[IncomeSource2] [varchar](1) NULL,
	[IncomeSource3] [varchar](1) NULL,
	[Allergies] [varchar](50) NULL,
	[SP_SMD] [bit] NULL,
	[SP_AlcoholDrug] [bit] NULL,
	[SP_Forensic] [bit] NULL,
	[SP_DD] [bit] NULL,
	[SP_MIMR] [bit] NULL,
	[SP_DUIDWI] [bit] NULL,
	[SP_Deaf] [bit] NULL,
	[SP_HearingImpaired] [bit] NULL,
	[SP_Blind] [bit] NULL,
	[SP_VisuallyImpaired] [bit] NULL,
	[SP_PhyDisabled] [bit] NULL,
	[SP_SpeechImpaired] [bit] NULL,
	[SP_PhysicalAbuse] [bit] NULL,
	[SP_SexualAbuse] [bit] NULL,
	[SP_DomesticViolence] [bit] NULL,
	[SP_ChildAlcDrug] [bit] NULL,
	[SP_HIVAIDS] [bit] NULL,
	[SP_Suicidal] [bit] NULL,
	[SP_SchoolDropOut] [bit] NULL,
	[SP_ProbationParole] [bit] NULL,
	[SP_GeneralPopulation] [bit] NULL,
	[LastEdit] [datetime] NOT NULL,
	[LastEditBy] [varchar](4) NULL,
 CONSTRAINT [PK_Demographics] PRIMARY KEY CLUSTERED 
(
	[Demographics_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Patient]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Patient](
	[Patient_Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ehr_id] [bigint] NULL,
	[CaseNumber] [varchar](50) NULL,
	[Name] [nvarchar](50) NULL,
	[FirstName] [nvarchar](50) NOT NULL,
	[MiddleInitial] [nchar](1) NULL,
	[LastName] [nvarchar](50) NOT NULL,
	[Suffix] [nchar](10) NULL,
	[Alias] [nvarchar](50) NULL,
	[SSN] [varchar](20) NULL,
	[CaseStatus] [char](1) NULL,
	[LastEdit] [datetime] NOT NULL,
	[LastEditBy] [char](4) NULL,
 CONSTRAINT [PK_Patient] PRIMARY KEY CLUSTERED 
(
	[Patient_Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ehr]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ehr](
	[system_id] [varchar](50) NULL,
	[ehr_id] [bigint] IDENTITY(1,1) NOT NULL,
	[subject_id] [bigint] NOT NULL,
	[time_created] [datetime] NOT NULL,
	[ehr_access_id] [bigint] NULL,
	[ehr_status_id] [bigint] NULL,
	[directory] [bigint] NULL,
	[contributions] [bigint] NULL,
	[compositions] [bigint] NULL,
 CONSTRAINT [PK_Ehr] PRIMARY KEY CLUSTERED 
(
	[ehr_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  View [dbo].[VDisplayDemographics]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VDisplayDemographics]
AS
SELECT     dbo.Demographics.Gender, dbo.Demographics.DOB, dbo.Demographics.Race, dbo.Demographics.Veteran, dbo.Demographics.Religion, 
                      dbo.Demographics.MaritalStatus, dbo.Demographics.EducationLevel, dbo.Demographics.EducationType, dbo.Demographics.LivingArrangement, 
                      dbo.Demographics.Employment, dbo.Demographics.IncomeSource1, dbo.Demographics.IncomeSource2, dbo.Demographics.IncomeSource3, 
                      dbo.Demographics.Allergies, dbo.Demographics.SP_SMD, dbo.Demographics.SP_AlcoholDrug, dbo.Demographics.SP_Forensic, dbo.Demographics.SP_DD, 
                      dbo.Demographics.SP_MIMR, dbo.Demographics.SP_DUIDWI, dbo.Demographics.SP_Deaf, dbo.Demographics.SP_HearingImpaired, dbo.Demographics.SP_Blind, 
                      dbo.Demographics.SP_VisuallyImpaired, dbo.Demographics.SP_PhyDisabled, dbo.Demographics.SP_SpeechImpaired, dbo.Demographics.SP_PhysicalAbuse, 
                      dbo.Demographics.SP_SexualAbuse, dbo.Demographics.SP_DomesticViolence, dbo.Demographics.SP_ChildAlcDrug, dbo.Demographics.SP_HIVAIDS, 
                      dbo.Demographics.SP_Suicidal, dbo.Demographics.SP_SchoolDropOut, dbo.Demographics.SP_ProbationParole, dbo.Demographics.SP_GeneralPopulation, 
                      dbo.Demographics.LastEdit, dbo.Demographics.LastEditBy, dbo.Patient.CaseNumber, dbo.Patient.Name, dbo.Patient.FirstName, dbo.Patient.MiddleInitial, 
                      dbo.Patient.LastName, dbo.Patient.Suffix, dbo.Patient.Alias, dbo.Patient.SSN, dbo.Patient.CaseStatus, dbo.Patient.LastEdit AS PatientLastEdit, 
                      dbo.Patient.LastEditBy AS PatientLastEditBy
FROM         dbo.Demographics INNER JOIN
                      dbo.Patient ON dbo.Demographics.Patient_Id = dbo.Patient.Patient_Id
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[32] 4[22] 2[19] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Patient"
            Begin Extent = 
               Top = 6
               Left = 865
               Bottom = 125
               Right = 1025
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Demographics"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 239
               Right = 232
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 51
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VDisplayDemographics'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N' = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 2085
         Alias = 1755
         Table = 1965
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VDisplayDemographics'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VDisplayDemographics'
GO
/****** Object:  Table [dbo].[Tickler]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Tickler](
	[PATID] [varchar](7) NOT NULL,
	[HIPAA] [datetime] NULL,
	[ISP] [datetime] NULL,
	[AOD_Goal] [datetime] NULL,
	[ISP6] [datetime] NULL,
	[LOC_Ad] [datetime] NULL,
	[LOC_CS] [datetime] NULL,
	[HTH_HX] [datetime] NULL,
	[FAF] [datetime] NULL,
	[FAF_XX] [bit] NULL,
	[DA] [datetime] NULL,
	[OOCi] [datetime] NULL,
	[OOCP] [datetime] NULL,
	[OOCY] [datetime] NULL,
	[LastEdit] [datetime] NULL,
	[LastEditBy] [varchar](4) NULL,
 CONSTRAINT [PK_Tickler] PRIMARY KEY CLUSTERED 
(
	[PATID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SystemLog]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SystemLog](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[PATID] [varchar](7) NOT NULL,
	[EventDate] [datetime] NULL,
	[SQLStatement] [varchar](max) NULL,
	[StaffId] [varchar](4) NOT NULL,
	[Workstation] [varchar](16) NULL,
 CONSTRAINT [PK_SystemLog] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SessionIdLog]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SessionIdLog](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[SessionId] [varchar](500) NOT NULL,
	[SessionIdExpire] [bigint] NOT NULL,
 CONSTRAINT [PK_SessionIdLog] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_SessionIdLog_SessionId] UNIQUE NONCLUSTERED 
(
	[SessionId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ServiceCodes]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ServiceCodes](
	[Service] [varchar](3) NOT NULL,
	[Description] [varchar](50) NULL,
	[TemplateID] [varchar](15) NULL,
 CONSTRAINT [PK_ServiceCodes] PRIMARY KEY CLUSTERED 
(
	[Service] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'110', N'Pharmacological Mgt. - Evaluation', N'104')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'120', N'Diagnostic Assessment', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'210', N'BH Counseling and Therapy - Individual', N'102')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'220', N'BH Counseling and Therapy - Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'235', N'ODADAS (AOD Family/Couple Counseling)', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'390', N'CPST Individual - Primary', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'391', N'CPST Individual - Secondary', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'392', N'CPST Group - Primary', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'393', N'CPST Group - Secondary', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'394', N'CPST Mont. Co.', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'395', N'CPST Intake', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'437', N'Pharmacological Mgt. - Assessment', N'103')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'440', N'Pharmacological Mgt. - Ongoing', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'441', N'Pharmacological Mgt. - Aftercare', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'510', N'Doors Job Development', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'511', N'Doors Job Coaching', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'512', N'Doors Benefits Consultation', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'513', N'SupEmp - Voc Services Individual', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'514', N'SupEmp - Macsis (CSP Individual)', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'520', N'Doors Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'521', N'SupEmp - BVR Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'522', N'SupEmp - BSVI Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'523', N'SupEmp - Voc Services Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'524', N'SupEmp - Macsis (CSP Group)', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'710', N'ODADAS - Individual AOD Counseling', N'102')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'720', N'ODADAS - Group AOD Counseling', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'913', N'SupEmp - NON-BILLABLE', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'982', N'Not able to attend', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'983', N'Kid refuses services', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'984', N'MIA/AWOL', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'985', N'NO SHOW (INCARCERATION)', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'988', N'Inappropriate For Services', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'989', N'No Show For Intake', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'996', N'NO SHOW (HOSPITALIZATION)', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'997', N'CANCELLATION', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'998', N'NO SHOW', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'999', N'NON-BILLABLE', NULL)
/****** Object:  Table [form].[ProgressNote]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [form].[ProgressNote](
	[TicketNum] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[JSONString] [varchar](max) NULL,
	[EntryDate] [datetime] NULL,
 CONSTRAINT [PK_ProgressNote] PRIMARY KEY CLUSTERED 
(
	[TicketNum] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Assignments]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Assignments](
	[AssignID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[PATID] [varchar](7) NOT NULL,
	[CreateDate] [datetime] NULL,
	[FromDate] [datetime] NULL,
	[ToDate] [datetime] NULL,
	[Disposition] [smallint] NULL,
	[Type] [varchar](15) NULL,
	[StaffId] [varchar](4) NULL,
	[Program] [varchar](3) NULL,
	[LastEdit] [datetime] NULL,
	[LastEditBy] [varchar](4) NULL,
 CONSTRAINT [PK_Assignments] PRIMARY KEY CLUSTERED 
(
	[AssignID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AddressData]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AddressData](
	[ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[PATID] [varchar](7) NOT NULL,
	[Descriptor] [varchar](15) NULL,
	[Title] [varchar](35) NULL,
	[Street1] [varchar](35) NULL,
	[Street2] [varchar](35) NULL,
	[City] [varchar](25) NULL,
	[State] [varchar](2) NULL,
	[Zip] [varchar](15) NULL,
	[County] [varchar](15) NULL,
	[Phone1] [varchar](15) NULL,
	[Phone1Desc] [varchar](25) NULL,
	[Phone2] [varchar](15) NULL,
	[Phone2Desc] [varchar](25) NULL,
	[LastEdit] [datetime] NULL,
	[LastEditBy] [varchar](4) NULL,
 CONSTRAINT [PK_AddressData] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [form].[Form_list]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [form].[Form_list](
	[FormName] [varchar](60) NOT NULL,
	[FormUrl] [varchar](75) NOT NULL,
	[Header] [varchar](30) NULL,
	[FormOrder] [smallint] NULL,
 CONSTRAINT [PK_Form_list] PRIMARY KEY CLUSTERED 
(
	[FormName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [form].[Form_list] ([FormName], [FormUrl], [Header], [FormOrder]) VALUES (N'Community Psychiatric Supportive Treatment Progress Note', N'http://ewsql/echarts-asp/101CPSTEdit.asp', N'Progress Notes', 2)
INSERT [form].[Form_list] ([FormName], [FormUrl], [Header], [FormOrder]) VALUES (N'General Mental Health Services Acknowledgement', N'http://ewsql/echarts-asp/105GMHServiceAck.asp', N'Miscellaneous', 10)
INSERT [form].[Form_list] ([FormName], [FormUrl], [Header], [FormOrder]) VALUES (N'Individual Progress Note', N'http://ewsql/echarts-asp/102IPNEdit.asp', N'Progress Notes', 3)
INSERT [form].[Form_list] ([FormName], [FormUrl], [Header], [FormOrder]) VALUES (N'ISP Review', N'http://ewsql/echarts-asp/noForm.asp', N'Individualized Service Plan', 9)
INSERT [form].[Form_list] ([FormName], [FormUrl], [Header], [FormOrder]) VALUES (N'ISP Update', N'http://ewsql/echarts-asp/noForm.asp', N'Individualized Service Plan', 8)
INSERT [form].[Form_list] ([FormName], [FormUrl], [Header], [FormOrder]) VALUES (N'New ISP', N'http://ewsql/echarts-asp/GandO.asp', N'Individualized Service Plan', 7)
INSERT [form].[Form_list] ([FormName], [FormUrl], [Header], [FormOrder]) VALUES (N'Phamacological Management/Nursing Progress Note', N'http://ewsql/echarts-asp/103PM-NPNEdit.asp', N'Progress Notes', 4)
INSERT [form].[Form_list] ([FormName], [FormUrl], [Header], [FormOrder]) VALUES (N'Pharmacological Management Progress Note', N'http://ewsql/echarts-asp/104PharmEdit.asp', N'Progress Notes', 5)
/****** Object:  Table [dbo].[DiagnosisData]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DiagnosisData](
	[ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[PATID] [varchar](7) NULL,
	[Date] [datetime] NULL,
	[Axis1a] [varchar](7) NULL,
	[Axis1b] [varchar](7) NULL,
	[Axis1c] [varchar](7) NULL,
	[Axis1d] [varchar](7) NULL,
	[Axis1e] [varchar](7) NULL,
	[Axis2a] [varchar](7) NULL,
	[Axis2b] [varchar](7) NULL,
	[Axis2c] [varchar](7) NULL,
	[Axis3] [varchar](250) NULL,
	[Axis4] [varchar](250) NULL,
	[CurrentGAF] [smallint] NULL,
	[HighestGAF] [smallint] NULL,
	[LastEdit] [datetime] NULL,
	[LastEditBy] [varchar](4) NULL,
 CONSTRAINT [PK_DiagnosisData] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DiagnosisAxis2]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiagnosisAxis2](
	[ICD9] [nvarchar](8) NULL,
	[Description] [nvarchar](50) NULL,
	[DSMIV] [nvarchar](8) NULL,
	[508k] [bit] NOT NULL,
	[TabDiagnosis] [nvarchar](50) NULL
) ON [PRIMARY]
GO
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.0  ', N' PARANOID PERSONALITY  ', N'301.0  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.20 ', N' SCHIZOID PERSONALITY NOS  ', N'301.20 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.22 ', N' SCHIZOTYPAL PERSON DIS  ', N'301.22 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.4  ', N' OBSESSIVE-COMPULSIVE PERSONALITY DIS  ', N'301.4  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.50 ', N' HISTRIONIC PERSON NOS  ', N'301.50 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.6  ', N' DEPENDENT PERSONALITY  ', N'301.6  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.7  ', N' ANTISOCIAL PERSONALITY  ', N'301.7  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.81 ', N' NARCISSISTIC PERSONALITY  ', N'301.81 ', 0, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.82 ', N' AVOIDANT PERSONALITY DIS  ', N'301.82 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.83 ', N' BORDERLINE PERSONALITY  ', N'301.83 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.9  ', N' PERSONALITY DISORDER NOS  ', N'301.9  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'317  ', N' MILD MENTAL RETARDATION  ', N'317  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'318.0  ', N' MOD MENTAL RETARDATION  ', N'318.0  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'318.1  ', N' SEVERE MENTAL RETARDAT  ', N'318.1  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'318.2  ', N' PROFOUND MENTAL RETARDAT  ', N'318.2  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'319  ', N' MENTAL RETARDATION NOS  ', N'319  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.89 ', N' BORDERLINE INTELLECTUAL FUNCTIONING', N'V62.89 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V71.09 ', N' NO DIAGNOSIS ON AXIS II', N'V71.09 ', 0, NULL)
/****** Object:  Table [dbo].[DiagnosisAxis1]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiagnosisAxis1](
	[ICD9] [nvarchar](8) NULL,
	[Description] [nvarchar](50) NULL,
	[DSMIV] [nvarchar](8) NULL,
	[508k] [bit] NOT NULL,
	[TabDiagnosis] [nvarchar](50) NULL
) ON [PRIMARY]
GO
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'290.40 ', N' VASCULAR DEMENTIA,UNCOMP  ', N'290.40 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'290.41 ', N' VASC DEMENTIA W DELIRIUM  ', N'290.41 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'290.42 ', N' VASC DEMENTIA W DELUSION  ', N'290.42 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'290.43 ', N' VASC DEMENTIA W DEPRESSN  ', N'290.43 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.0  ', N' DELIRIUM TREMENS  ', N'291.0  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.1  ', N' ALCOHOL AMNESTIC DISORDR  ', N'291.1  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.2  ', N' ALCOHOL PERSIST DEMENTIA  ', N'291.2  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.3  ', N' ALCOH PSY DIS W HALLUCIN  ', N'291.3  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.5  ', N' ALCOH PSYCH DIS W DELUS  ', N'291.5  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.81 ', N' ALCOHOL WITHDRAWAL  ', N'291.81 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.89 ', N' ALCOHOL MENTAL DISOR NEC  ', N'291.89 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.9  ', N' ALCOHOL MENTAL DISOR NOS  ', N'291.9  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.0  ', N' DRUG WITHDRAWAL  ', N'292.0  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.11 ', N' DRUG PSYCH DISOR W DELUS  ', N'292.11 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.12 ', N' DRUG PSY DIS W HALLUCIN  ', N'292.12 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.81 ', N' DRUG-INDUCED DELIRIUM  ', N'292.81 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.82 ', N' DRUG PERSISTING DEMENTIA  ', N'292.82 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.83 ', N' DRUG PERSIST AMNESTC DIS  ', N'292.83 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.84 ', N' DRUG-INDUCED MOOD DISORD  ', N'292.84 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.89 ', N' DRUG MENTAL DISORDER NEC  ', N'292.89 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.9  ', N' DRUG MENTAL DISORDER NOS  ', N'292.9  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'293.0  ', N' DELIRIUM D/T OTHER COND  ', N'293.0  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'293.81 ', N' PSY DIS W DELUS OTH DIS  ', N'293.81 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'293.82 ', N' PSY DIS W HALLUC OTH DIS  ', N'293.82 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'293.83 ', N' MOOD DISORDER DUE TO MEDICAL CONDITION', N'293.83 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.0  ', N' AMNESTIC DISORD OTH DIS  ', N'294.0  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.1  ', N' DEMENTIA W/OUT BEHAV. DISTURB', N'294.1  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.11 ', N' DEMENTIA W BEHAVIOR DIST  ', N'294.11 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.8  ', N' DEMENTIA NOS/AMNESTIC DIS NOS', N'294.8  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.9  ', N' COGNITIVE DIS NOS', N'294.9  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.10 ', N' SCHIZOPHRENIA, DISORGANIZED', N'295.10 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.20 ', N' SCHIZOPHRENIA, CATATONIC', N'295.20 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.30 ', N' SCHIZOPHRENIA, PARANOID', N'295.30 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.40 ', N' SCHIZOPHRENIFORM DIS NOS  ', N'295.40 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.60 ', N' SCHIZOPHRENIA, RESIDUAL', N'295.60 ', 0, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.70 ', N' SCHIZOAFFECTIVE DIS BIPOLAR/DEPRESSED', N'295.70 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.90 ', N' SCHIZOPHRENIA, UNDIFFERENTIATED', N'295.90 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.00 ', N' BIPOL 1, SINGLE MANIC EP, UNSPEC', N'296.00 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.01 ', N' BIPOL 1, SINGLE MANIC EP, MILD', N'296.01 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.02 ', N' BIPOL 1, SINGLE MANIC EP, MOD', N'296.02 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.03 ', N' BIPOL 1, SINGLE MANIC EP, SEV W/O PSYCH', N'296.03 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.04 ', N' BIPOL 1, SINGLE MANIC EP, SEV W PSYCH', N'296.04 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.05 ', N' BIPOL 1, SINGLE MANIC EP, PART REMISS', N'296.05 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.06 ', N' BIPOL 1, SINGLE MANIC EP, FULL REMISS', N'296.06 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.20 ', N' MAJ DEPR D/O, SING EP, UNSPEC', N'296.20 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.21 ', N' MAJ DEPR D/O, SING EP, MILD', N'296.21 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.22 ', N' MAJ DEPR D/O, SING EP, MOD', N'296.22 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.23 ', N' MAJ DEPR D/O, SING EP, SEV W/O PSYCH', N'296.23 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.24 ', N' MAJ DEPR D/O, SING EP, SEV W PSYCH', N'296.24 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.25 ', N' MAJ DEPR D/O, SING EP, PART REMISS', N'296.25 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.26 ', N' MAJ DEPR D/O, SING EP, FULL REMISS', N'296.26 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.30 ', N' MAJ DEPR D/O, RECURR, UNSPEC', N'296.30 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.31 ', N' MAJ DEPR D/O, RECURR, MILD', N'296.31 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.32 ', N' MAJ DEPR D/O, RECURR, MOD', N'296.32 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.33 ', N' MAJ DEPR D/O, RECURR, SEV W/O PSYCH', N'296.33 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.34 ', N' MAJ DEPR D/O, RECURR, SEV W PSYCH', N'296.34 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.35 ', N' MAJ DEPR D/O, RECURR, PART REMISS', N'296.35 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.36 ', N' MAJ DEPR D/O, RECURR, FULL REMISS', N'296.36 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.40 ', N' BIPOL 1, MRE HYPOMANIC', N'296.40 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.41 ', N' BIPOL 1, MRE MANIC, MILD', N'296.41 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.42 ', N' BIPOL 1, MRE MANIC, MOD', N'296.42 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.43 ', N' BIPOL 1, MRE MANIC, SEV W/O PSYCH', N'296.43 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.44 ', N' BIPOL 1, MRE MANIC, SEV W PSYCH', N'296.44 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.45 ', N' BIPOL 1, MRE MANIC, PART REMISS', N'296.45 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.46 ', N' BIPOL 1, MRE MANIC, FULL REMISS', N'296.46 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.50 ', N' BIPOL 1, MRE DEPR, UNSPEC', N'296.50 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.51 ', N' BIPOL 1, MRE DEPR, MILD', N'296.51 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.52 ', N' BIPOL 1, MRE DEPR, MOD', N'296.52 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.53 ', N' BIPOL 1, MRE DEPR, SEV W/O PSYCH', N'296.53 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.54 ', N' BIPOL 1, MRE DEPR, SEV W PSYCH', N'296.54 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.55 ', N' BIPOL 1, MRE DEPR, PART REMISS', N'296.55 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.56 ', N' BIPOL 1, MRE DEPR, FULL REMISS', N'296.56 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.60 ', N' BIPOL 1, MRE MIXED, UNSPEC', N'296.60 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.61 ', N' BIPOL 1, MRE MIXED, MILD', N'296.61 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.62 ', N' BIPOL 1, MRE MIXED, MOD', N'296.62 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.63 ', N' BIPOL 1, MRE MIXED, SEV W/O PSYCH', N'296.63 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.64 ', N' BIPOL 1, MRE MIXED, SEV W PSYCH', N'296.64 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.65 ', N' BIPOL 1, MRE MIXED, PART REMISS', N'296.65 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.66 ', N' BIPOL 1, MRE MIXED, FULL REMISS', N'296.66 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.7  ', N' BIPOL 1, MRE UNSPECIFIED', N'296.7  ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.80 ', N' BIPOLAR DISORDER NOS', N'296.80 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.89 ', N' BIPOLAR-II DISORDER', N'296.89 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.90 ', N' MOOD DISORDER NOS  ', N'296.90 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'297.1  ', N' DELUSIONAL DISORDER  ', N'297.1  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'297.3  ', N' SHARED PSYCHOTIC DISORDER', N'297.3  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'298.8  ', N' BRIEF PSYCHOTIC DISORDER', N'298.8  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'298.9  ', N' PSYCHOSIS NOS  ', N'298.9  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'299.00 ', N' AUTISTIC DISORD-CURRENT  ', N'299.00 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'299.1  ', N' CHILDHD DISINTEGR DIS  ', N'299.1  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'299.80 ', N' RETT''S/ASPERGER''S/PERVASIVE DISORDER', N'299.80 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.00 ', N' ANXIETY STATE NOS  ', N'300.00 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.01 ', N' PANIC DIS W/O AGORPHOBIA  ', N'300.01 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.02 ', N' GENERALIZED ANXIETY DIS  ', N'300.02 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.11 ', N' CONVERSION DISORDER  ', N'300.11 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.12 ', N' DISSOCIATIVE AMNESIA  ', N'300.12 ', 1, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.13 ', N' DISSOCIATIVE FUGUE  ', N'300.13 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.14 ', N' DISSOCIATVE IDENTITY DIS  ', N'300.14 ', 1, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.15 ', N' DISSOCIATIVE REACT NOS  ', N'300.15 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.16 ', N' FACTITIOUS DIS W SYMPTOM  ', N'300.16 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.19 ', N' FACTITIOUS ILL NEC/NOS  ', N'300.19 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.21 ', N' PANIC DIS WITH AGORAPHOBIA', N'300.21 ', 0, N'Neurotic Disorders')
GO
print 'Processed 100 total records'
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.22 ', N' AGORAPHOBIA W/O PANIC  ', N'300.22 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.23 ', N' SOCIAL PHOBIA  ', N'300.23 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.29 ', N' SPECIFIC PHOBIA', N'300.29 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.3  ', N' OBSESSIVE-COMPULSIVE DIS  ', N'300.3  ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.4  ', N' DYSTHYMIC DISORDER  ', N'300.4  ', 1, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.6  ', N' DEPERSONALIZATION DISORD  ', N'300.6  ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.7  ', N' HYPOCHONDRIASIS/BODY DYSMORPHIC DISORDER  ', N'300.7  ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.81 ', N' SOMATIZATION DISORDER  ', N'300.81 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.82 ', N' UNDIFF SOMATOFORM DISRDR  ', N'300.82 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.9  ', N' UNSPECIFIED MENTAL DIS (NONPSYCHOTIC)', N'300.9  ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.13 ', N' CYCLOTHYMIC DISORDER  ', N'301.13 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.2  ', N' PEDOPHILIA  ', N'302.2  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.3  ', N' TRANSVESTIC FETISHISM  ', N'302.3  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.4  ', N' EXHIBITIONISM  ', N'302.4  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.6  ', N' GENDR IDENTITY DIS-CHILD  ', N'302.6  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.70 ', N' PSYCHOSEXUAL DYSFUNC NOS  ', N'302.70 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.71 ', N' HYPOACTIVE SEX DESIRE  ', N'302.71 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.72 ', N' INHIBITED SEX EXCITEMENT  ', N'302.72 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.73 ', N' FEMALE ORGASMIC DISORDER  ', N'302.73 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.74 ', N' MALE ORGASMIC DISORDER  ', N'302.74 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.75 ', N' PREMATURE EJACULATION  ', N'302.75 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.76 ', N' DYSPAREUNIA,PSYCHOGENIC  ', N'302.76 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.79 ', N' SEXUAL AVERSION DISORDER', N'302.79 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.81 ', N' FETISHISM  ', N'302.81 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.82 ', N' VOYEURISM  ', N'302.82 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.83 ', N' SEXUAL MASOCHISM  ', N'302.83 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.84 ', N' SEXUAL SADISM  ', N'302.84 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.85 ', N' GEND IDEN DIS,ADOL/ADULT  ', N'302.85 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.89 ', N' FROTTEURISM', N'302.89 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.9  ', N' PARAPHILIA NOS', N'302.9  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'303.00 ', N' AC ALCOHOL INTOX-UNSPEC  ', N'303.00 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'303.90 ', N' ALCOH DEP NEC/NOS-UNSPEC  ', N'303.90 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'303.93 ', N' ALCOH DEP NEC/NOS-REMISS  ', N'303.93 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.00 ', N' OPIOID DEPENDENCE-UNSPEC  ', N'304.00 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.03 ', N' OPIOID DEPENDENCE-REMISS  ', N'304.03 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.10 ', N' SED,HYP,ANXIOLYT DEP-NOS  ', N'304.10 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.13 ', N' SED,HYP,ANXIOLYT DEP-REM  ', N'304.13 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.20 ', N' COCAINE DEPEND-UNSPEC  ', N'304.20 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.23 ', N' COCAINE DEPEND-REMISS  ', N'304.23 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.30 ', N' CANNABIS DEPEND-UNSPEC  ', N'304.30 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.33 ', N' CANNABIS DEPEND-REMISS  ', N'304.33 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.40 ', N' AMPHETAMIN DEPEND-UNSPEC  ', N'304.40 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.43 ', N' AMPHETAMIN DEPEND-REMISS  ', N'304.43 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.50 ', N' HALLUCINOGEN DEP-UNSPEC  ', N'304.50 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.53 ', N' HALLUCINOGEN DEP-REMISS  ', N'304.53 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.60 ', N' DRUG DEPEND NEC-UNSPEC  ', N'304.60 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.63 ', N' DRUG DEPEND NEC-IN REM  ', N'304.63 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.80 ', N' POLYSUBSTANCE DEPENDENCE', N'304.80 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.83 ', N' POLYSUBSTANCE DEPENDENCE, IN REMISSION', N'304.83 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.90 ', N' DRUG DEPEND NOS-UNSPEC  ', N'304.90 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.00 ', N' ALCOHOL ABUSE-UNSPEC  ', N'305.00 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.03 ', N' ALCOHOL ABUSE-IN REMISS  ', N'305.03 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.1  ', N' TOBACCO USE DISORDER  ', N'305.1  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.20 ', N' CANNABIS ABUSE-UNSPEC  ', N'305.20 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.23 ', N' CANNABIS ABUSE-IN REMISS  ', N'305.23 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.30 ', N' HALLUCINOG ABUSE-UNSPEC  ', N'305.30 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.33 ', N' HALLUCINOG ABUSE-REMISS  ', N'305.33 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.40 ', N' SED,HYP,ANXIOLYTC AB-NOS  ', N'305.40 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.43 ', N' SED,HYP,ANXIOLYTC AB-REM  ', N'305.43 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.50 ', N' OPIOID ABUSE-UNSPEC  ', N'305.50 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.53 ', N' OPIOID ABUSE-IN REMISS  ', N'305.53 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.60 ', N' COCAINE ABUSE-UNSPEC  ', N'305.60 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.63 ', N' COCAINE ABUSE-IN REMISS  ', N'305.63 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.70 ', N' AMPHETAMINE ABUSE-UNSPEC  ', N'305.70 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.73 ', N' AMPHETAMINE ABUSE-REMISS  ', N'305.73 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.90 ', N' DRUG ABUSE NEC-UNSPEC  ', N'305.90 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.93 ', N' DRUG ABUSE NEC-IN REMISS  ', N'305.93 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'306.51 ', N' PSYCHOGENIC VAGINISMUS  ', N'306.51 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.0  ', N' STUTTERING  ', N'307.0  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.1  ', N' ANOREXIA NERVOSA  ', N'307.1  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.20 ', N' TIC DISORDER NOS  ', N'307.20 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.21 ', N' TRANSIENT TIC DISORDER  ', N'307.21 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.22 ', N' CHR MOTOR/VOCAL TIC DIS  ', N'307.22 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.23 ', N' TOURETTE''S DISORDER  ', N'307.23 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.3  ', N' STEREOTYPIC MOVEMENT DIS  ', N'307.3  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.42 ', N' PERSISTENT INSOMNIA  ', N'307.42 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.44 ', N' PERSISTENT HYPERSOMNIA  ', N'307.44 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.45 ', N' CIRCADIAN RHYTHM SLEEP  ', N'307.45 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.46 ', N' SLEEP TERROR/SLEEPWALKING DISORDER  ', N'307.46 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.47 ', N' DYSSOMNIA/PARASOMNIA NOS/NIGHTMARE DISORDER', N'307.47 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.50 ', N' EATING DISORDER NOS  ', N'307.50 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.51 ', N' BULIMIA NERVOSA  ', N'307.51 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.52 ', N' PICA  ', N'307.52 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.53 ', N' RUMINATION DISORDER  ', N'307.53 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.59 ', N' FEEDING DIS OF INFANCY/CHILD', N'307.59 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.6  ', N' ENURESIS  ', N'307.6  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.7  ', N' ENCOPRESIS  ', N'307.7  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.8  ', N' PSYCHOGENIC PAIN DISORD  ', N'307.8  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.80 ', N' PSYCHOGENIC PAIN NOS  ', N'307.80 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.9  ', N' COMMUNICATION DISORDER NOS', N'307.9  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'308.3  ', N' ACUTE STRESS REACT NEC  ', N'308.3  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.0  ', N' ADJUSTMNT DIS W DEPRESSN  ', N'309.0  ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.21 ', N' SEPARATION ANXIETY  ', N'309.21 ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.24 ', N' ADJUSTMENT DIS W ANXIETY  ', N'309.24 ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.28 ', N' ADJUST DIS W ANXIETY/DEP  ', N'309.28 ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.3  ', N' ADJUST DISOR/DIS CONDUCT  ', N'309.3  ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.4  ', N' ADJ DIS-EMOTION/CONDUCT  ', N'309.4  ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.81 ', N' POSTTRAUMATIC STRESS DIS  ', N'309.81 ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.9  ', N' ADJUSTMENT REACTION NOS  ', N'309.9  ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'311  ', N' DEPRESSIVE DISORDER NOS', N'311  ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.30 ', N' IMPULSE CONTROL DIS NOS  ', N'312.30 ', 0, N'Childhood Disorder')
GO
print 'Processed 200 total records'
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.31 ', N' PATHOLOGICAL GAMBLING  ', N'312.31 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.32 ', N' KLEPTOMANIA  ', N'312.32 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.33 ', N' PYROMANIA  ', N'312.33 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.34 ', N' INTERMITT EXPLOSIVE DIS  ', N'312.34 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.39 ', N' TRICHTILLOMANIA', N'312.39 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.81 ', N' CNDCT DSRDR CHLDHD ONST  ', N'312.81 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.82 ', N' CNDCT DSRDR ADLSCNT ONST  ', N'312.82 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.89 ', N' OTHER CONDUCT DISORDER  ', N'312.89 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.9  ', N' CONDUCT DISTURBANCE NOS  ', N'312.9  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'313.23 ', N' SELECTIVE MUTISM  ', N'313.23 ', 1, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'313.81 ', N' OPPOSITION DEFIANT DISOR  ', N'313.81 ', 1, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'313.89 ', N' REACTIVE ATTACHMENT DIS', N'313.89 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'313.9  ', N' EMOTIONAL DIS CHILD NOS  ', N'313.9  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'314.00 ', N' ADHD, INATTENTIVE TYPE', N'314.00 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'314.01 ', N' ADHD, HYPERACTIVE/IMPULSIVE/COMBINED TYPE', N'314.01 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'314.9  ', N' ADHD, NOS', N'314.9  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.00 ', N' READING DISORDER NOS  ', N'315.00 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.1  ', N' MATHEMATICS DISORDER  ', N'315.1  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.2  ', N' DISORDER OF WRITTEN EXPRESSION', N'315.2  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.31 ', N' EXPRESSIVE LANGUAGE DIS  ', N'315.31 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.32 ', N' RECP-EXPRES LANGUAGE DIS  ', N'315.32 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.39 ', N' PHONOLOGICAL DISORDER', N'315.39 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.4  ', N' DEVEL COORDINATION DIS  ', N'315.4  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.9  ', N' LEARNING DISORDER NOS', N'315.9  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'316  ', N' PSYCHIC FACTOR W OTH DIS  ', N'316  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.10 ', N' CONSL PARTNER PROB  ', N'V61.10 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.11 ', N' CNSL VICTM PARTNER ABUSE  ', N'V61.11 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.12 ', N' CNSL PERP PARTNER ABUSE  ', N'V61.12 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.20 ', N' CNSL PRNT-CHLD PROB NOS  ', N'V61.20 ', 0, N'Family Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.21 ', N' CNSL VICTIM CHILD ABUSE  ', N'V61.21 ', 0, N'Family Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.8  ', N' SIBLING RELATIONAL PROB', N'V61.8  ', 0, N'Family Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.9  ', N' FAMILY CIRCUMSTANCE NOS  ', N'V61.9  ', 0, N'Family Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.2  ', N' OCCUP CIRCUMSTANCES NEC  ', N'V62.2  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.3  ', N' EDUCATIONAL CIRCUMSTANCE  ', N'V62.3  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.4  ', N' SOCIAL MALADJUSTMENT  ', N'V62.4  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.81 ', N' INTERPERSONAL PROBL NEC  ', N'V62.81 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.82 ', N' BEREAVEMENT, UNCOMPLICAT  ', N'V62.82 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.89 ', N' PSYCHOLOGICAL STRESS NEC  ', N'V62.89 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V71.01 ', N' OBSV-ADULT ANTISOC BEHAV  ', N'V71.01 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V71.02 ', N' OBSV-ADOLESC ANTISOC BEH  ', N'V71.02 ', 0, NULL)
/****** Object:  Table [form].[ISPObjectives]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [form].[ISPObjectives](
	[ObjectiveID] [numeric](18, 0) NOT NULL,
	[ISPID] [numeric](18, 0) NOT NULL,
	[Goal] [char](1) NULL,
	[Objective] [char](1) NULL,
	[Description] [char](400) NULL,
	[StartDate] [datetime] NULL,
	[ClientWill] [char](200) NULL,
	[Duration] [char](30) NULL,
	[OtherWill] [char](100) NULL,
	[NotClinIndicated] [char](3) NULL,
	[TherInt1] [char](250) NULL,
	[ServDesc1] [char](30) NULL,
	[Freq1] [char](20) NULL,
	[ProvType1] [char](75) NULL,
	[TherInt2] [char](250) NULL,
	[ServDesc2] [char](30) NULL,
	[Freq2] [char](20) NULL,
	[ProvType2] [char](75) NULL,
	[TherInt3] [char](250) NULL,
	[ServDesc3] [char](30) NULL,
	[Freq3] [char](20) NULL,
	[ProvType3] [char](75) NULL,
	[LastEdit] [datetime] NULL,
	[LastEditBy] [varchar](4) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [form].[ISPGoals]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [form].[ISPGoals](
	[GoalID] [numeric](18, 0) NOT NULL,
	[ISPID] [numeric](18, 0) NOT NULL,
	[Goal] [char](1) NULL,
	[TreatRecommendNo] [char](10) NULL,
	[ServicePlanType] [char](25) NULL,
	[Dated] [datetime] NULL,
	[StartDate] [datetime] NULL,
	[TargetCompletionDate] [datetime] NULL,
	[AdjustedTargetDate] [datetime] NULL,
	[ReasonforAdjustment] [char](100) NULL,
	[Outcomes] [char](200) NULL,
	[GoalCollaboration] [char](500) NULL,
	[ClientReviewed] [char](3) NULL,
	[ClientAgrees] [char](3) NULL,
	[DesiredResults] [char](300) NULL,
	[StrengthsUsedtoMeetGoal] [char](300) NULL,
	[SkillsKnowledgeNeeded] [char](200) NULL,
	[NaturalCommunitySupports] [char](200) NULL,
	[ActualDateofGoalCompletion] [datetime] NULL,
	[GoalDiscontinueDate] [datetime] NULL,
	[ReasonforDiscontinuation] [char](200) NULL,
	[ProgressNote] [datetime] NULL,
	[LastEdit] [datetime] NULL,
	[LastEditBy] [varchar](4) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [form].[ISP_ServiceDescriptions]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [form].[ISP_ServiceDescriptions](
	[ID] [numeric](18, 0) NOT NULL,
	[ServiceDescriptions] [varchar](30) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(2 AS Numeric(18, 0)), N'MH Assessment')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(4 AS Numeric(18, 0)), N'MH Pharm. Mgmt')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(5 AS Numeric(18, 0)), N'BH Individ Counseling/Ther')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(6 AS Numeric(18, 0)), N'BH Group Counseling/Ther')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(7 AS Numeric(18, 0)), N'CPST Individ Svcs')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(8 AS Numeric(18, 0)), N'CPST Group Svcs')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(20 AS Numeric(18, 0)), N'------------------------------')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(21 AS Numeric(18, 0)), N'AoD Crisis Intervention')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(25 AS Numeric(18, 0)), N'AoD Individual Counseling')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(26 AS Numeric(18, 0)), N'AoD Group Counseling')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(27 AS Numeric(18, 0)), N'AoD Case Management')
/****** Object:  Table [form].[ISP]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [form].[ISP](
	[ISPID] [numeric](18, 0) NOT NULL,
	[ClientID] [char](7) NULL,
	[AgencyName1] [char](50) NULL,
	[ContactandTitle1] [char](75) NULL,
	[ServicesProvided1] [char](50) NULL,
	[AgencyName2] [char](50) NULL,
	[ContactandTitle2] [char](75) NULL,
	[ServicesProvided2] [char](50) NULL,
	[AgencyName3] [char](50) NULL,
	[ContactandTitle3] [char](75) NULL,
	[ServicesProvided3] [char](50) NULL,
	[AgencyName4] [char](50) NULL,
	[ContactandTitle4] [char](75) NULL,
	[ServicesProvided4] [char](50) NULL,
	[AgencyName5] [char](50) NULL,
	[ContactandTitle5] [char](75) NULL,
	[ServicesProvided5] [char](50) NULL,
	[AoDAdultLevelofCare] [char](10) NULL,
	[AoDAdultLevelofCareNA] [bit] NULL,
	[AoDYouthLevelofCare] [char](10) NULL,
	[AoDYouthLevelofCareNA] [bit] NULL,
	[AnticipatedDate] [datetime] NULL,
	[LevelofCare] [char](750) NULL,
	[ClientSignatureDate] [datetime] NULL,
	[ClientProvidedaCopy] [char](3) NULL,
	[ParentSignatureDate] [datetime] NULL,
	[ProviderID] [varchar](4) NULL,
	[ProviderSignatureDate] [datetime] NULL,
	[SupervisorID] [varchar](4) NULL,
	[SupervisorSignatureDate] [datetime] NULL,
	[PhysicianID] [varchar](4) NULL,
	[PhysicianSignatureDate] [datetime] NULL,
	[LastEdit] [datetime] NULL,
	[LastEditBy] [varchar](4) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Messages]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Messages](
	[ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[PATID] [varchar](7) NOT NULL,
	[MessageType] [smallint] NULL,
	[CreationTimestamp] [datetime] NULL,
	[Message] [varchar](max) NULL,
	[ParentID] [numeric](18, 0) NULL,
	[LastEdit] [datetime] NULL,
	[LastEditBy] [varchar](4) NULL,
 CONSTRAINT [PK_Messages] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[JobClass]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[JobClass](
	[JobClassID] [smallint] IDENTITY(1,1) NOT NULL,
	[JobClassName] [varchar](25) NULL,
	[Permission] [binary](4) NULL,
 CONSTRAINT [PK_JobClass] PRIMARY KEY CLUSTERED 
(
	[JobClassID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[JobClass] ON
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (1, N'Administrator', 0x00000001)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (2, N'Psychiatrist', 0x00000002)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (3, N'Nurse', 0x00000003)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (5, N'Comm Support Specialist', 0x00000004)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (6, N'Therapist', 0x00000005)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (7, N'Res Support Specialist', 0x00000006)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (8, N'Clinical Administrator', 0x00000007)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (9, N'Human Resources', 0x00000008)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (10, N'Accounts Recievable', 0x00000009)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (11, N'General Admin', 0x0000000A)
INSERT [dbo].[JobClass] ([JobClassID], [JobClassName], [Permission]) VALUES (12, N'Unprivileged', NULL)
SET IDENTITY_INSERT [dbo].[JobClass] OFF
/****** Object:  Table [dbo].[Codes]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Codes](
	[CodeID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[ColumnName] [varchar](20) NULL,
	[Value] [varchar](3) NULL,
	[Descriptor] [varchar](60) NULL,
 CONSTRAINT [PK_Codes] PRIMARY KEY CLUSTERED 
(
	[CodeID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Codes] ON
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(1 AS Numeric(18, 0)), N'CaseStatus', N'A', N'Active')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(2 AS Numeric(18, 0)), N'CaseStatus', N'D', N'Terminated')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(3 AS Numeric(18, 0)), N'CaseStatus', N'P', N'Pre-Admit')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(4 AS Numeric(18, 0)), N'Gender', N'M', N'Male')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(5 AS Numeric(18, 0)), N'Gender', N'F', N'Female')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(6 AS Numeric(18, 0)), N'Suffix', N'II', N'II')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(7 AS Numeric(18, 0)), N'Suffix', N'III', N'III')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(8 AS Numeric(18, 0)), N'Suffix', N'JR', N'JR')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(9 AS Numeric(18, 0)), N'Suffix', N'SR', N'SR')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(10 AS Numeric(18, 0)), N'MaritalStatus', N'S', N'Single (Never Married)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(11 AS Numeric(18, 0)), N'MaritalStatus', N'D', N'Divorced')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(12 AS Numeric(18, 0)), N'MaritalStatus', N'M', N'Married')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(13 AS Numeric(18, 0)), N'MaritalStatus', N'O', N'Other')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(14 AS Numeric(18, 0)), N'MaritalStatus', N'W', N'Widowed')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(17 AS Numeric(18, 0)), N'LivingArrangement', N'A', N'Own Home')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(18 AS Numeric(18, 0)), N'LivingArrangement', N'B', N'Friend''s Home')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(19 AS Numeric(18, 0)), N'LivingArrangement', N'C', N'Relative''s Home')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(20 AS Numeric(18, 0)), N'LivingArrangement', N'D', N'Supervised Group Living')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(21 AS Numeric(18, 0)), N'LivingArrangement', N'E', N'Supervised Apartment')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(22 AS Numeric(18, 0)), N'LivingArrangement', N'F', N'Boarding Home')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(23 AS Numeric(18, 0)), N'LivingArrangement', N'G', N'Crisis Residential')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(24 AS Numeric(18, 0)), N'LivingArrangement', N'H', N'Child Foster Care')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(25 AS Numeric(18, 0)), N'LivingArrangement', N'I', N'Adult Foster Care')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(26 AS Numeric(18, 0)), N'LivingArrangement', N'J', N'Intermediate Care Facility')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(27 AS Numeric(18, 0)), N'LivingArrangement', N'K', N'Skilled Nursing Facility')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(28 AS Numeric(18, 0)), N'LivingArrangement', N'L', N'Resite Care')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(29 AS Numeric(18, 0)), N'LivingArrangement', N'M', N'Mental Retardation Intermediate Care Facility')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(30 AS Numeric(18, 0)), N'LivingArrangement', N'N', N'Licensed Mental Retardation Intermediate Care Facililty')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(31 AS Numeric(18, 0)), N'LivingArrangement', N'O', N'State Mental Retardation Institution')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(32 AS Numeric(18, 0)), N'LivingArrangement', N'P', N'State Mental Health Institution')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(33 AS Numeric(18, 0)), N'LivingArrangement', N'Q', N'Hospital')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(34 AS Numeric(18, 0)), N'LivingArrangement', N'R', N'Correctional Facility')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(35 AS Numeric(18, 0)), N'LivingArrangement', N'S', N'Homeless')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(36 AS Numeric(18, 0)), N'LivingArrangement', N'T', N'Rest Home')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(37 AS Numeric(18, 0)), N'LivingArrangement', N'U', N'Other')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(38 AS Numeric(18, 0)), N'EducationLevel', N'00', N'Less than Grade 1')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(39 AS Numeric(18, 0)), N'EducationLevel', N'01', N'Grade 1')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(40 AS Numeric(18, 0)), N'EducationLevel', N'02', N'Grade 2')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(41 AS Numeric(18, 0)), N'EducationLevel', N'03', N'Grade 3')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(42 AS Numeric(18, 0)), N'EducationLevel', N'04', N'Grade 4')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(43 AS Numeric(18, 0)), N'EducationLevel', N'05', N'Grade 5')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(44 AS Numeric(18, 0)), N'EducationLevel', N'06', N'Grade 6')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(45 AS Numeric(18, 0)), N'EducationLevel', N'07', N'Grade 7')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(46 AS Numeric(18, 0)), N'EducationLevel', N'08', N'Grade 8')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(47 AS Numeric(18, 0)), N'EducationLevel', N'09', N'Grade 9')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(48 AS Numeric(18, 0)), N'EducationLevel', N'10', N'Grade 10')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(49 AS Numeric(18, 0)), N'EducationLevel', N'11', N'Grade 11')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(50 AS Numeric(18, 0)), N'EducationLevel', N'12', N'High School Diploma/GED')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(51 AS Numeric(18, 0)), N'EducationLevel', N'13', N'Trade or Technical School')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(52 AS Numeric(18, 0)), N'EducationLevel', N'14', N'Some College')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(53 AS Numeric(18, 0)), N'EducationLevel', N'15', N'2 Year College/Associate Degree')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(54 AS Numeric(18, 0)), N'EducationLevel', N'16', N'4 Year College/Undergrad. Degree')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(55 AS Numeric(18, 0)), N'EducationLevel', N'17', N'Graduate Courses')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(56 AS Numeric(18, 0)), N'EducationLevel', N'18', N'Graduate Degree')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(57 AS Numeric(18, 0)), N'EducationLevel', N'19', N'Post-Graduate Studies')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(58 AS Numeric(18, 0)), N'EducationLevel', N'20', N'Further Specialized Studies')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(59 AS Numeric(18, 0)), N'EducationType', N'1', N'Regular')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(60 AS Numeric(18, 0)), N'EducationType', N'2', N'Severe Behavioral Handicaped (SBH)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(61 AS Numeric(18, 0)), N'EducationType', N'3', N'Learning Disabled (LD)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(62 AS Numeric(18, 0)), N'EducationType', N'4', N'Hearing Impaired (HI)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(63 AS Numeric(18, 0)), N'EducationType', N'5', N'Visually Impaired (VI)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(64 AS Numeric(18, 0)), N'EducationType', N'6', N'Multihandicapped')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(65 AS Numeric(18, 0)), N'EducationType', N'7', N'Developmentally Disabled (DD)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(66 AS Numeric(18, 0)), N'EducationType', N'8', N'Orthopedically Handicapped (OH)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(67 AS Numeric(18, 0)), N'EducationType', N'9', N'Other')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(68 AS Numeric(18, 0)), N'Employment', N'A', N'Full Time (35 Hrs+ per week)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(69 AS Numeric(18, 0)), N'Employment', N'B', N'Part Time (<35 Hrs per week)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(70 AS Numeric(18, 0)), N'Employment', N'C', N'Sheltered Employment')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(71 AS Numeric(18, 0)), N'Employment', N'D', N'Unemployed')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(72 AS Numeric(18, 0)), N'Employment', N'E', N'Homemaker')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(73 AS Numeric(18, 0)), N'Employment', N'F', N'Student')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(74 AS Numeric(18, 0)), N'Employment', N'G', N'Retired')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(75 AS Numeric(18, 0)), N'Employment', N'H', N'Disabled')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(76 AS Numeric(18, 0)), N'Employment', N'I', N'Inmate of Institution')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(77 AS Numeric(18, 0)), N'Employment', N'J', N'Other')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(78 AS Numeric(18, 0)), N'IncomeSource', N'A', N'Wages/Salary Income')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(79 AS Numeric(18, 0)), N'IncomeSource', N'B', N'Family or Relative')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(80 AS Numeric(18, 0)), N'IncomeSource', N'C', N'Alimony')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(81 AS Numeric(18, 0)), N'IncomeSource', N'D', N'Child Support')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(82 AS Numeric(18, 0)), N'IncomeSource', N'E', N'Savings or Investments')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(83 AS Numeric(18, 0)), N'IncomeSource', N'F', N'Disability Insurance/Worker''s Compensation')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(84 AS Numeric(18, 0)), N'IncomeSource', N'G', N'Unemployment Compensation')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(85 AS Numeric(18, 0)), N'IncomeSource', N'H', N'Retirement Pension')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(86 AS Numeric(18, 0)), N'IncomeSource', N'I', N'Social Security Retirement')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(87 AS Numeric(18, 0)), N'IncomeSource', N'J', N'General Relief/Welfare')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(88 AS Numeric(18, 0)), N'IncomeSource', N'K', N'Aid for a Dependent Child (ADC or AFD)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(89 AS Numeric(18, 0)), N'IncomeSource', N'L', N'Supplemental Security Income (SSI)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(90 AS Numeric(18, 0)), N'IncomeSource', N'M', N'Social Security Disability Insurance (SSDI)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(91 AS Numeric(18, 0)), N'IncomeSource', N'N', N'Other')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(92 AS Numeric(18, 0)), N'IncomeSource', N'P', N'None')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(93 AS Numeric(18, 0)), N'Race', N'A', N'Asian')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(94 AS Numeric(18, 0)), N'Race', N'B', N'Black or African American')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(95 AS Numeric(18, 0)), N'Race', N'M', N'Alaskan Native')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(96 AS Numeric(18, 0)), N'Race', N'N', N'American Indian')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(97 AS Numeric(18, 0)), N'Race', N'P', N'Hispanic')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(98 AS Numeric(18, 0)), N'Race', N'U', N'Unknown')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(99 AS Numeric(18, 0)), N'Race', N'W', N'White')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(100 AS Numeric(18, 0)), N'LevelofCare', N'A1', N'Consultation and Early Intervention (Level 0.5)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(101 AS Numeric(18, 0)), N'LevelofCare', N'B1', N'Outpatient Services (Level I-A)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(102 AS Numeric(18, 0)), N'LevelofCare', N'B2', N'Intensive Outpatient Services (Level I-B)')
GO
print 'Processed 100 total records'
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(103 AS Numeric(18, 0)), N'LevelofCare', N'B3', N'Day Treatment (Level I-C)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(104 AS Numeric(18, 0)), N'LevelofCare', N'C1', N'Non-Medical Community Residential Treatment (Level II-A)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(105 AS Numeric(18, 0)), N'LevelofCare', N'C2', N'Medical Community Residential (Level II-B)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(106 AS Numeric(18, 0)), N'LevelofCare', N'D1', N'Ambulatory Detoxification (Level III-A)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(107 AS Numeric(18, 0)), N'LevelofCare', N'D2', N'23-Hours Observation Bed (Level III-B)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(108 AS Numeric(18, 0)), N'LevelofCare', N'D3', N'Sub-Acute Detoxification (Level IV)')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(109 AS Numeric(18, 0)), N'LevelofCare', N'NA', N'Not Applicable')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(110 AS Numeric(18, 0)), N'County', NULL, N'Adams')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(111 AS Numeric(18, 0)), N'County', NULL, N'Butler')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(112 AS Numeric(18, 0)), N'County', NULL, N'Champaign')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(113 AS Numeric(18, 0)), N'County', NULL, N'Clark')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(114 AS Numeric(18, 0)), N'County', NULL, N'Clermont')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(115 AS Numeric(18, 0)), N'County', NULL, N'Clinton')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(116 AS Numeric(18, 0)), N'County', NULL, N'Darke')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(117 AS Numeric(18, 0)), N'County', NULL, N'Defiance')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(118 AS Numeric(18, 0)), N'County', NULL, N'Fairfield')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(119 AS Numeric(18, 0)), N'County', NULL, N'Franklin')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(120 AS Numeric(18, 0)), N'County', NULL, N'Greene')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(121 AS Numeric(18, 0)), N'County', NULL, N'Hamilton')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(122 AS Numeric(18, 0)), N'County', NULL, N'Licking')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(123 AS Numeric(18, 0)), N'County', NULL, N'Lucas')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(124 AS Numeric(18, 0)), N'County', NULL, N'Marion')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(125 AS Numeric(18, 0)), N'County', NULL, N'Miami')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(126 AS Numeric(18, 0)), N'County', NULL, N'Monroe')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(127 AS Numeric(18, 0)), N'County', NULL, N'Montgomery')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(128 AS Numeric(18, 0)), N'County', NULL, N'Morrow')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(129 AS Numeric(18, 0)), N'County', NULL, N'Paulding')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(130 AS Numeric(18, 0)), N'County', NULL, N'Preble')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(131 AS Numeric(18, 0)), N'County', NULL, N'Richland')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(132 AS Numeric(18, 0)), N'County', NULL, N'Shelby')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(133 AS Numeric(18, 0)), N'County', NULL, N'Trumbull')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(134 AS Numeric(18, 0)), N'County', NULL, N'Tuscarawas')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(135 AS Numeric(18, 0)), N'County', NULL, N'Union')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(136 AS Numeric(18, 0)), N'County', NULL, N'Unknown')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(137 AS Numeric(18, 0)), N'County', NULL, N'Warren')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(139 AS Numeric(18, 0)), N'Disposition', N'1', N'Open')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(140 AS Numeric(18, 0)), N'Disposition', N'0', N'Closed')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(141 AS Numeric(18, 0)), N'MessageType', N'1', N'Referral Message')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(142 AS Numeric(18, 0)), N'MessageType', N'2', N'Case Management Message')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(143 AS Numeric(18, 0)), N'MessageType', N'3', N'Therapy Message')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(144 AS Numeric(18, 0)), N'MessageType', N'4', N'Medication Message')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(145 AS Numeric(18, 0)), N'MessageType', N'5', N'Bulletin Message')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(146 AS Numeric(18, 0)), N'MessageType', N'6', N'Triage Message')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(147 AS Numeric(18, 0)), N'AddressType', NULL, N'Main')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(148 AS Numeric(18, 0)), N'AddressType', NULL, N'Emergency')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(149 AS Numeric(18, 0)), N'AddressType', NULL, N'Guardian')
INSERT [dbo].[Codes] ([CodeID], [ColumnName], [Value], [Descriptor]) VALUES (CAST(150 AS Numeric(18, 0)), N'AddressType', NULL, N'Employer')
SET IDENTITY_INSERT [dbo].[Codes] OFF

/****** Object:  Table [form].[BillingStrip]    Script Date: 04/09/2010 12:39:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [form].[BillingStrip](
	[ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[FormName] [varchar](15) NOT NULL,
	[TicketNum] [numeric](18, 0) NOT NULL,
	[PATID] [varchar](7) NULL,
	[Date] [datetime] NULL,
	[Service] [varchar](3) NULL,
	[Program] [varchar](3) NULL,
	[StaffId] [varchar](4) NULL,
	[Location1] [varchar](3) NULL,
	[Location2] [varchar](2) NULL,
	[BeginTime] [datetime] NULL,
	[EndTime] [datetime] NULL,
	[Minutes] [smallint] NULL,
	[Mod1] [char](2) NULL,
	[Mod2] [char](2) NULL,
	[EntryDate] [datetime] NULL,
	[Signed] [char](1) NULL,
	[SignatureID] [numeric](18, 0) NULL,
	[NoteType] [char](1) NULL,
 CONSTRAINT [PK_Billing_Strip] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  StoredProcedure [dbo].[isSessionExpired]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[isSessionExpired] (
		@sessionid varchar(500),
		@status bit OUTPUT
	)
As
BEGIN
	SET NOCOUNT ON;
	DECLARE @sessionExpire datetime2 = (SELECT DATEADD(ss, (SELECT SessionIdExpire FROM [EW-EHR].[dbo].[SessionIdLog] WHERE SessionId = @sessionid)/1000, '1970-01-01 00:00:00'))
	IF @sessionExpire < GETUTCDATE()
		SELECT @status = 1 /** Session has expired **/
	ELSE
		SELECT @status = 0 /** Session has not expired **/
END
GO
/****** Object:  View [dbo].[VTicklerList]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VTicklerList]
AS
select PATID, HIPAA as 'Date', 'HIPAA' as 'ItemName' from TICKLER
UNION
select PATID, ISP as 'Date', 'ISP' as 'ItemName' from TICKLER
UNION
select PATID, AOD_Goal as 'Date', 'AOD_Goal' as 'ItemName' from TICKLER
UNION
select PATID, ISP6 as 'Date', 'ISP6' as 'ItemName' from TICKLER
UNION
select PATID, LOC_Ad as 'Date', 'LOC_Ad' as 'ItemName' from TICKLER
UNION
select PATID, LOC_CS as 'Date', 'LOC_CS' as 'ItemName' from TICKLER
UNION
select PATID, HTH_HX as 'Date', 'HTH_HX' as 'ItemName' from TICKLER
UNION
select PATID, FAF as 'Date', 'FAF' as 'ItemName' from TICKLER
UNION
select PATID, DA as 'Date', 'DA' as 'ItemName' from TICKLER
UNION
select PATID, OOCi as 'Date', 'OOCi' as 'ItemName' from TICKLER
UNION
select PATID, OOCP as 'Date', 'OOCP' as 'ItemName' from TICKLER
UNION
select PATID, OOCY as 'Date', 'OOCY' as 'ItemName' from TICKLER
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VTicklerList'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VTicklerList'
GO
/****** Object:  View [dbo].[VSuffix]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VSuffix]
AS
SELECT     Value AS Suffix, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'Suffix')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VSuffix'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VSuffix'
GO
/****** Object:  View [dbo].[VRace]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VRace]
AS
SELECT     Value AS Race, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'Race')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VRace'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VRace'
GO
/****** Object:  View [dbo].[VMessageType]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VMessageType]
AS
SELECT     Value AS MessageType, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'MessageType')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VMessageType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VMessageType'
GO
/****** Object:  View [dbo].[VMaritalStatus]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VMaritalStatus]
AS
SELECT     TOP (100) PERCENT Value AS MaritalStatus, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'MaritalStatus')
ORDER BY MaritalStatus
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VMaritalStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VMaritalStatus'
GO
/****** Object:  View [dbo].[VMainAddress]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VMainAddress]
AS
SELECT     ID, PATID, Descriptor, Title, Street1, Street2, City, State, Zip, County, Phone1, Phone1Desc, Phone2, Phone2Desc, LastEdit, LastEditBy
FROM         dbo.AddressData
WHERE     (Descriptor = 'MAIN')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "AddressData"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VMainAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VMainAddress'
GO
/****** Object:  View [dbo].[VLivingArrangement]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VLivingArrangement]
AS
SELECT     Value AS LivingArrangement, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'LivingArrangement')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VLivingArrangement'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VLivingArrangement'
GO
/****** Object:  View [dbo].[VLevelofCare]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VLevelofCare]
AS
SELECT     Value AS LevelofCare, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'LevelofCare')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VLevelofCare'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VLevelofCare'
GO
/****** Object:  View [dbo].[VIncomeSource]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VIncomeSource]
AS
SELECT     Value AS IncomeSource, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'IncomeSource')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VIncomeSource'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VIncomeSource'
GO
/****** Object:  View [dbo].[VGender]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VGender]
AS
SELECT     Value AS Gender, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'Gender')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VGender'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VGender'
GO
/****** Object:  View [dbo].[VEmployment]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VEmployment]
AS
SELECT     Value AS Employment, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'Employment')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VEmployment'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VEmployment'
GO
/****** Object:  View [dbo].[VEducationType]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VEducationType]
AS
SELECT     Value AS EducationType, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'EducationType')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VEducationType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VEducationType'
GO
/****** Object:  View [dbo].[VEducationLevel]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VEducationLevel]
AS
SELECT     Value AS EducationLevel, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'EducationLevel')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VEducationLevel'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VEducationLevel'
GO
/****** Object:  View [dbo].[VDisposition]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VDisposition]
AS
SELECT     Value AS Disposition, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'Disposition')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VDisposition'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VDisposition'
GO
/****** Object:  Table [dbo].[User]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[User](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](15) NOT NULL,
	[StaffId] [varchar](4) NOT NULL,
	[Password] [varbinary](50) NULL,
	[JobClassID] [smallint] NULL,
	[extperm] [bit] NULL,
	[StaffName] [varchar](35) NULL,
	[Program] [varchar](3) NULL,
	[HireDate] [datetime] NULL,
	[Status] [varchar](10) NULL,
	[TermDate] [datetime] NULL,
	[Office] [varchar](15) NULL,
	[OfficePhone] [varchar](12) NULL,
	[OfficeExt] [varchar](4) NULL,
	[StaffDescription] [varchar](25) NULL,
	[StaffNPI] [varchar](15) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_User_StaffId] UNIQUE NONCLUSTERED 
(
	[StaffId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_User_Username] UNIQUE NONCLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  View [dbo].[VCounty]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VCounty]
AS
SELECT     Descriptor AS County, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'County')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[41] 4[20] 2[8] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1785
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VCounty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VCounty'
GO
/****** Object:  View [dbo].[VCaseStatus]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/****** Script for SelectTopNRows command from SSMS  ******/
CREATE VIEW [dbo].[VCaseStatus]
AS
SELECT     Value AS CaseStatus, Descriptor
FROM         dbo.Codes
WHERE     (ColumnName = 'CaseStatus')
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Codes"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 123
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VCaseStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VCaseStatus'
GO
/****** Object:  View [dbo].[VActiveReferral]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VActiveReferral]
AS
SELECT     ID, PATID, Program, UCI, UPID, ReferralSource, ReferralType, ReferralDate, TakenByStaff, AdmissionDate, LevelofCare, PlanType, LastService, DischargeDate, 
                      Disposition, LastEdit, LastEditBy
FROM         dbo.Referral
WHERE     (DischargeDate IS NULL)
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Referral"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VActiveReferral'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VActiveReferral'
GO
/****** Object:  Table [dbo].[UserSessionMap]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserSessionMap](
	[SessionId] [int] NOT NULL,
	[UserId] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  StoredProcedure [dbo].[getStaffId]    Script Date: 04/09/2010 12:39:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getStaffId] 
	@sessionid varchar(500),
	@staffid varchar(4) OUTPUT
AS
BEGIN
	SET NOCOUNT ON;
	SELECT @staffid = (SELECT StaffId FROM[User] WHERE [User].Id = (SELECT UserId FROM [UserSessionMap] inner join [SessionIdLog] on [UserSessionMap].SessionId=[SessionIdLog].Id WHERE [SessionIdLog].SessionId =@sessionid));
END
GO
/****** Object:  Default [DF_Tickler_LastEdit]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Tickler] ADD  CONSTRAINT [DF_Tickler_LastEdit]  DEFAULT (getdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_SystemLog_EventDate]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[SystemLog] ADD  CONSTRAINT [DF_SystemLog_EventDate]  DEFAULT (getdate()) FOR [EventDate]
GO
/****** Object:  Default [DF_Referral_UPID]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Referral] ADD  CONSTRAINT [DF_Referral_UPID]  DEFAULT ('01396') FOR [UPID]
GO
/****** Object:  Default [DF_Referral_LastEdit]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Referral] ADD  CONSTRAINT [DF_Referral_LastEdit]  DEFAULT (getdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_ProgressNote_EntryDate]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [form].[ProgressNote] ADD  CONSTRAINT [DF_ProgressNote_EntryDate]  DEFAULT (getdate()) FOR [EntryDate]
GO
/****** Object:  Default [DF_Assignments_CreateDate]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Assignments] ADD  CONSTRAINT [DF_Assignments_CreateDate]  DEFAULT (getdate()) FOR [CreateDate]
GO
/****** Object:  Default [DF_Assignments_LastEdit]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Assignments] ADD  CONSTRAINT [DF_Assignments_LastEdit]  DEFAULT (getdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_AddressData_LastEdit]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[AddressData] ADD  CONSTRAINT [DF_AddressData_LastEdit]  DEFAULT (getdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_DiagnosisData_LastEdit]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[DiagnosisData] ADD  CONSTRAINT [DF_DiagnosisData_LastEdit]  DEFAULT (getdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_Messages_CreationTimestamp]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Messages] ADD  CONSTRAINT [DF_Messages_CreationTimestamp]  DEFAULT (getdate()) FOR [CreationTimestamp]
GO
/****** Object:  Default [DF_Messages_LastEdit]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Messages] ADD  CONSTRAINT [DF_Messages_LastEdit]  DEFAULT (getdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_BillingStrip_EntryDate]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [form].[BillingStrip] ADD  CONSTRAINT [DF_BillingStrip_EntryDate]  DEFAULT (getdate()) FOR [EntryDate]
GO
/****** Object:  ForeignKey [FK_Demographics_PatientId]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Demographics]  WITH CHECK ADD  CONSTRAINT [FK_Demographics_PatientId] FOREIGN KEY([Patient_Id])
REFERENCES [dbo].[Patient] ([Patient_Id])
GO
ALTER TABLE [dbo].[Demographics] CHECK CONSTRAINT [FK_Demographics_PatientId]
GO
/****** Object:  ForeignKey [FK_Patient_Ehr]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Patient]  WITH CHECK ADD  CONSTRAINT [FK_Patient_Ehr] FOREIGN KEY([ehr_id])
REFERENCES [dbo].[Ehr] ([ehr_id])
GO
ALTER TABLE [dbo].[Patient] CHECK CONSTRAINT [FK_Patient_Ehr]
GO
/****** Object:  ForeignKey [FK_Ehr_Ehr]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [dbo].[Ehr]  WITH CHECK ADD  CONSTRAINT [FK_Ehr_Ehr] FOREIGN KEY([subject_id])
REFERENCES [dbo].[Patient] ([Patient_Id])
GO
ALTER TABLE [dbo].[Ehr] CHECK CONSTRAINT [FK_Ehr_Ehr]
GO
/****** Object:  ForeignKey [FK_ProgressNote_ProgressNote]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [form].[ProgressNote]  WITH CHECK ADD  CONSTRAINT [FK_ProgressNote_ProgressNote] FOREIGN KEY([TicketNum])
REFERENCES [form].[ProgressNote] ([TicketNum])
GO
ALTER TABLE [form].[ProgressNote] CHECK CONSTRAINT [FK_ProgressNote_ProgressNote]
GO
/****** Object:  ForeignKey [FK_BillingStrip_ProgressNote]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [form].[BillingStrip]  WITH CHECK ADD  CONSTRAINT [FK_BillingStrip_ProgressNote] FOREIGN KEY([ID])
REFERENCES [form].[ProgressNote] ([TicketNum])
GO
ALTER TABLE [form].[BillingStrip] CHECK CONSTRAINT [FK_BillingStrip_ProgressNote]
GO
/****** Object:  ForeignKey [FK_BillingStrip_ServiceCodes]    Script Date: 04/09/2010 12:39:31 ******/
ALTER TABLE [form].[BillingStrip]  WITH CHECK ADD  CONSTRAINT [FK_BillingStrip_ServiceCodes] FOREIGN KEY([Service])
REFERENCES [dbo].[ServiceCodes] ([Service])
GO
ALTER TABLE [form].[BillingStrip] CHECK CONSTRAINT [FK_BillingStrip_ServiceCodes]
GO
/****** Object:  ForeignKey [FK_JobClass_JobClassID]    Script Date: 04/09/2010 12:39:32 ******/
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_JobClass_JobClassID] FOREIGN KEY([JobClassID])
REFERENCES [dbo].[JobClass] ([JobClassID])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_JobClass_JobClassID]
GO
/****** Object:  ForeignKey [FK_UserSessionMap_SessionIdLog]    Script Date: 04/09/2010 12:39:32 ******/
ALTER TABLE [dbo].[UserSessionMap]  WITH CHECK ADD  CONSTRAINT [FK_UserSessionMap_SessionIdLog] FOREIGN KEY([SessionId])
REFERENCES [dbo].[SessionIdLog] ([Id])
GO
ALTER TABLE [dbo].[UserSessionMap] CHECK CONSTRAINT [FK_UserSessionMap_SessionIdLog]
GO
/****** Object:  ForeignKey [FK_UserSessionMap_UserId]    Script Date: 04/09/2010 12:39:32 ******/
ALTER TABLE [dbo].[UserSessionMap]  WITH CHECK ADD  CONSTRAINT [FK_UserSessionMap_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[UserSessionMap] CHECK CONSTRAINT [FK_UserSessionMap_UserId]
GO
