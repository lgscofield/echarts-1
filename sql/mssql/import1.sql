/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
USE [echarts]
GO
/****** Object:  User [echarts]    Script Date: 05/21/2010 16:22:37 ******/
CREATE USER [echarts] FOR LOGIN [echarts] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Schema [form]    Script Date: 05/21/2010 16:22:37 ******/
CREATE SCHEMA [form] AUTHORIZATION [dbo]
GO
/****** Object:  Table [dbo].[Ehr]    Script Date: 05/21/2010 16:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ehr](
	[system_id] [varchar](50) NULL,
	[ehr_id] [bigint] NOT NULL,
	[subject_id] [bigint] NULL,
	[time_created] [datetime] NULL,
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
INSERT [dbo].[Ehr] ([system_id], [ehr_id], [subject_id], [time_created], [ehr_access_id], [ehr_status_id], [directory], [contributions], [compositions]) VALUES (NULL, 1, 1, CAST(0x00009D7B00FCE81C AS DateTime), NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Ehr] ([system_id], [ehr_id], [subject_id], [time_created], [ehr_access_id], [ehr_status_id], [directory], [contributions], [compositions]) VALUES (NULL, 2, 2, CAST(0x00009D7C0091DBA5 AS DateTime), NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Ehr] ([system_id], [ehr_id], [subject_id], [time_created], [ehr_access_id], [ehr_status_id], [directory], [contributions], [compositions]) VALUES (NULL, 3, 4, CAST(0x00009D7D01097129 AS DateTime), NULL, NULL, NULL, NULL, NULL)
/****** Object:  Table [dbo].[Demographics]    Script Date: 05/21/2010 16:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Demographics](
	[demographics_id] [bigint] NOT NULL,
	[patient_id] [bigint] NULL,
	[insurance_type] [varchar](20) NULL,
	[preferred_language] [varchar](50) NULL,
	[ethnicity] [varchar](50) NULL,
	[gender] [varchar](1) NULL,
	[dob] [datetime] NULL,
	[race] [varchar](1) NULL,
	[is_veteran] [bit] NULL,
	[religion] [varchar](50) NULL,
	[marital_status] [varchar](1) NULL,
	[education_level] [varchar](2) NULL,
	[education_type] [varchar](1) NULL,
	[living_arrangement] [varchar](1) NULL,
	[employment] [varchar](1) NULL,
	[income_sources] [varbinary](255) NULL,
	[income_source_1] [varchar](1) NULL,
	[income_source_2] [varchar](1) NULL,
	[income_source_3] [varchar](1) NULL,
	[allergies] [varbinary](255) NULL,
	[is_smd] [bit] NULL,
	[is_alcohol_drug] [bit] NULL,
	[is_forensic] [bit] NULL,
	[is_dd] [bit] NULL,
	[is_mimr] [bit] NULL,
	[is_duidwi] [bit] NULL,
	[is_deaf] [bit] NULL,
	[is_hearing_impaired] [bit] NULL,
	[is_blind] [bit] NULL,
	[is_visually_impaired] [bit] NULL,
	[is_phy_disabled] [bit] NULL,
	[is_speech_impaired] [bit] NULL,
	[is_physical_abuse] [bit] NULL,
	[is_sexual_abuse] [bit] NULL,
	[is_domestic_violence] [bit] NULL,
	[is_child_alc_drug] [bit] NULL,
	[is_hiv_aids] [bit] NULL,
	[is_suicidal] [bit] NULL,
	[is_school_dropout] [bit] NULL,
	[is_probation_parole] [bit] NULL,
	[is_general_population] [bit] NULL,
	[last_edit] [datetime] NOT NULL,
	[last_edit_by] [varchar](15) NULL,
 CONSTRAINT [PK_Demographics] PRIMARY KEY CLUSTERED 
(
	[demographics_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Demographics] ([demographics_id], [patient_id], [insurance_type], [preferred_language], [ethnicity], [gender], [dob], [race], [is_veteran], [religion], [marital_status], [education_level], [education_type], [living_arrangement], [employment], [income_sources], [income_source_1], [income_source_2], [income_source_3], [allergies], [is_smd], [is_alcohol_drug], [is_forensic], [is_dd], [is_mimr], [is_duidwi], [is_deaf], [is_hearing_impaired], [is_blind], [is_visually_impaired], [is_phy_disabled], [is_speech_impaired], [is_physical_abuse], [is_sexual_abuse], [is_domestic_violence], [is_child_alc_drug], [is_hiv_aids], [is_suicidal], [is_school_dropout], [is_probation_parole], [is_general_population], [last_edit], [last_edit_by]) VALUES (1, 1, NULL, NULL, NULL, NULL, CAST(0x00008C4C00000000 AS DateTime), NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, CAST(0x00009D7B00FCE81C AS DateTime), NULL)
INSERT [dbo].[Demographics] ([demographics_id], [patient_id], [insurance_type], [preferred_language], [ethnicity], [gender], [dob], [race], [is_veteran], [religion], [marital_status], [education_level], [education_type], [living_arrangement], [employment], [income_sources], [income_source_1], [income_source_2], [income_source_3], [allergies], [is_smd], [is_alcohol_drug], [is_forensic], [is_dd], [is_mimr], [is_duidwi], [is_deaf], [is_hearing_impaired], [is_blind], [is_visually_impaired], [is_phy_disabled], [is_speech_impaired], [is_physical_abuse], [is_sexual_abuse], [is_domestic_violence], [is_child_alc_drug], [is_hiv_aids], [is_suicidal], [is_school_dropout], [is_probation_parole], [is_general_population], [last_edit], [last_edit_by]) VALUES (2, 2, NULL, NULL, NULL, NULL, CAST(0x0000649F00000000 AS DateTime), NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, CAST(0x00009D7C0091DBA5 AS DateTime), NULL)
INSERT [dbo].[Demographics] ([demographics_id], [patient_id], [insurance_type], [preferred_language], [ethnicity], [gender], [dob], [race], [is_veteran], [religion], [marital_status], [education_level], [education_type], [living_arrangement], [employment], [income_sources], [income_source_1], [income_source_2], [income_source_3], [allergies], [is_smd], [is_alcohol_drug], [is_forensic], [is_dd], [is_mimr], [is_duidwi], [is_deaf], [is_hearing_impaired], [is_blind], [is_visually_impaired], [is_phy_disabled], [is_speech_impaired], [is_physical_abuse], [is_sexual_abuse], [is_domestic_violence], [is_child_alc_drug], [is_hiv_aids], [is_suicidal], [is_school_dropout], [is_probation_parole], [is_general_population], [last_edit], [last_edit_by]) VALUES (3, 3, NULL, NULL, NULL, NULL, CAST(0x000073E900000000 AS DateTime), NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, CAST(0x00009D7D01097129 AS DateTime), NULL)
INSERT [dbo].[Demographics] ([demographics_id], [patient_id], [insurance_type], [preferred_language], [ethnicity], [gender], [dob], [race], [is_veteran], [religion], [marital_status], [education_level], [education_type], [living_arrangement], [employment], [income_sources], [income_source_1], [income_source_2], [income_source_3], [allergies], [is_smd], [is_alcohol_drug], [is_forensic], [is_dd], [is_mimr], [is_duidwi], [is_deaf], [is_hearing_impaired], [is_blind], [is_visually_impaired], [is_phy_disabled], [is_speech_impaired], [is_physical_abuse], [is_sexual_abuse], [is_domestic_violence], [is_child_alc_drug], [is_hiv_aids], [is_suicidal], [is_school_dropout], [is_probation_parole], [is_general_population], [last_edit], [last_edit_by]) VALUES (4, 4, NULL, NULL, NULL, NULL, CAST(0x000073DC00C5C100 AS DateTime), NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, CAST(0x00009D7D01098A9C AS DateTime), NULL)
/****** Object:  Table [dbo].[Alert]    Script Date: 05/21/2010 16:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Alert](
	[id] [int] NOT NULL,
	[patient_id] [bigint] NULL,
	[name] [varchar](50) NULL,
	[item_name] [varchar](50) NULL,
	[date] [datetime] NULL,
 CONSTRAINT [PK_Alert] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Patient]    Script Date: 05/21/2010 16:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Patient](
	[patient_id] [bigint] NOT NULL,
	[ehr_id] [bigint] NULL,
	[case_number] [varchar](50) NULL,
	[name] [varchar](50) NULL,
	[first_name] [varchar](50) NOT NULL,
	[middle_initial] [varchar](1) NULL,
	[last_name] [varchar](50) NOT NULL,
	[suffix] [varchar](50) NULL,
	[alias] [varchar](50) NULL,
	[ssn] [varchar](20) NULL,
	[case_status] [varchar](1) NULL,
	[last_edit] [datetime] NOT NULL,
	[last_edit_by] [varchar](15) NULL,
 CONSTRAINT [PK_Patient] PRIMARY KEY CLUSTERED 
(
	[patient_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Patient] ([patient_id], [ehr_id], [case_number], [name], [first_name], [middle_initial], [last_name], [suffix], [alias], [ssn], [case_status], [last_edit], [last_edit_by]) VALUES (1, 1, N'0000001', NULL, N'Harold', N'P', N'Test', N'Jr        ', N'Harry', N'123-456-7689', N'A', CAST(0x00009D7B00FCE700 AS DateTime), N'ihilt          ')
INSERT [dbo].[Patient] ([patient_id], [ehr_id], [case_number], [name], [first_name], [middle_initial], [last_name], [suffix], [alias], [ssn], [case_status], [last_edit], [last_edit_by]) VALUES (2, 2, N'0000002', NULL, N'Janice', N'G', N'Test', N'          ', N'Jan', N'132-465-7980', N'A', CAST(0x00009D7C0091DAC0 AS DateTime), N'ihilt          ')
INSERT [dbo].[Patient] ([patient_id], [ehr_id], [case_number], [name], [first_name], [middle_initial], [last_name], [suffix], [alias], [ssn], [case_status], [last_edit], [last_edit_by]) VALUES (3, 3, N'0000003', NULL, N'Charles', N'L', N'Test', N'Jr', N'Chuck', N'123-456-7890', N'A', CAST(0x00009D7D0109707D AS DateTime), N'ihilt')
INSERT [dbo].[Patient] ([patient_id], [ehr_id], [case_number], [name], [first_name], [middle_initial], [last_name], [suffix], [alias], [ssn], [case_status], [last_edit], [last_edit_by]) VALUES (4, 3, N'0000003', NULL, N'Charles', N'L', N'Test', N'Jr', N'Chuck', N'123-456-7890', N'A', CAST(0x00009D7D01098A9A AS DateTime), N'ihilt')
/****** Object:  View [dbo].[VDisplayDemographics]    Script Date: 05/21/2010 16:22:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VDisplayDemographics]
AS
SELECT     dbo.Demographics.Gender, dbo.Demographics.DOB, dbo.Demographics.Race, dbo.Demographics.is_veteran, dbo.Demographics.Religion, 
                      dbo.Demographics.marital_status, dbo.Demographics.education_level, dbo.Demographics.education_type, dbo.Demographics.living_arrangement, 
                      dbo.Demographics.Employment, dbo.Demographics.income_source_1, dbo.Demographics.income_source_2, dbo.Demographics.income_source_3, 
                      dbo.Demographics.Allergies, dbo.Demographics.is_smd, dbo.Demographics.is_alcohol_drug, dbo.Demographics.is_forensic, dbo.Demographics.is_dd, 
                      dbo.Demographics.is_mimr, dbo.Demographics.is_duidwi, dbo.Demographics.is_deaf, dbo.Demographics.is_hearing_impaired, dbo.Demographics.is_blind, 
                      dbo.Demographics.is_visually_impaired, dbo.Demographics.is_phy_disabled, dbo.Demographics.is_speech_impaired, dbo.Demographics.is_physical_abuse, 
                      dbo.Demographics.is_sexual_abuse, dbo.Demographics.is_domestic_violence, dbo.Demographics.is_child_alc_drug, dbo.Demographics.is_hiv_aids, 
                      dbo.Demographics.is_suicidal, dbo.Demographics.is_school_dropout, dbo.Demographics.is_probation_parole, dbo.Demographics.is_general_population, 
                      dbo.Demographics.last_edit, dbo.Demographics.last_edit_by, dbo.Patient.case_number, dbo.Patient.Name, dbo.Patient.first_name, dbo.Patient.middle_initial, 
                      dbo.Patient.last_name, dbo.Patient.Suffix, dbo.Patient.Alias, dbo.Patient.SSN, dbo.Patient.case_status, dbo.Patient.last_edit AS PatientLastEdit, 
                      dbo.Patient.last_edit_by AS PatientLastEditBy
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
/****** Object:  Table [dbo].[Referral]    Script Date: 05/21/2010 16:22:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Referral](
	[referral_id] [bigint] IDENTITY(1,1) NOT NULL,
	[ehr_id] [bigint] NOT NULL,
	[program] [varchar](10) NULL,
	[uci] [varchar](7) NULL,
	[upid] [varchar](5) NULL,
	[referral_source] [varchar](10) NULL,
	[referral_type] [varchar](5) NULL,
	[referral_date] [datetime] NULL,
	[taken_by_staff] [varchar](15) NULL,
	[admission_date] [datetime] NULL,
	[level_of_care] [varchar](2) NULL,
	[plan_type] [varchar](1) NULL,
	[last_service] [datetime] NULL,
	[discharge_date] [datetime] NULL,
	[disposition] [varchar](1) NULL,
	[last_edit] [datetime] NULL,
	[last_edit_by] [varchar](15) NULL,
 CONSTRAINT [PK_Referral] PRIMARY KEY CLUSTERED 
(
	[referral_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Tickler]    Script Date: 05/21/2010 16:22:40 ******/
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
	[LastEditBy] [varchar](15) NULL,
 CONSTRAINT [PK_Tickler] PRIMARY KEY CLUSTERED 
(
	[PATID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SystemLog]    Script Date: 05/21/2010 16:22:40 ******/
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
/****** Object:  Table [dbo].[session_id_log]    Script Date: 05/21/2010 16:22:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[session_id_log](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[session_id] [varchar](500) NOT NULL,
	[session_id_expire] [bigint] NOT NULL,
 CONSTRAINT [PK_SessionIdLog] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_SessionIdLog_SessionId] UNIQUE NONCLUSTERED 
(
	[session_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [form].[ProgressNote]    Script Date: 05/21/2010 16:22:40 ******/
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
/****** Object:  Table [dbo].[DiagnosisData]    Script Date: 05/21/2010 16:22:40 ******/
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
	[LastEditBy] [varchar](15) NULL,
 CONSTRAINT [PK_DiagnosisData] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  StoredProcedure [dbo].[isSessionExpired]    Script Date: 05/21/2010 16:22:41 ******/
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
	DECLARE @sessionExpire datetime2 = (SELECT DATEADD(ss, (SELECT session_id_expire FROM [echarts].[dbo].[session_id_log] WHERE session_id = @sessionid)/1000, '1970-01-01 00:00:00'))
	DECLARE @now datetime2 = (SELECT GETUTCDATE())

	IF @sessionExpire < @now OR @sessionExpire IS Null
		SELECT @status = 1 /** Session has expired **/
	ELSE
		SELECT @status = 0 /** Session has not expired **/
END
GO
/****** Object:  Table [form].[ISPObjectives]    Script Date: 05/21/2010 16:22:41 ******/
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
	[LastEditBy] [varchar](15) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [form].[ISPGoals]    Script Date: 05/21/2010 16:22:41 ******/
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
	[LastEditBy] [varchar](15) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [form].[ISP]    Script Date: 05/21/2010 16:22:41 ******/
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
	[LastEditBy] [varchar](15) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[hibernate_sequences]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[hibernate_sequences](
	[sequence_name] [varchar](50) NOT NULL,
	[sequence_next_hi_value] [int] NULL,
 CONSTRAINT [PK_hibernate_sequences] PRIMARY KEY CLUSTERED 
(
	[sequence_name] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[hibernate_sequences] ([sequence_name], [sequence_next_hi_value]) VALUES (N'Demographics', 5)
INSERT [dbo].[hibernate_sequences] ([sequence_name], [sequence_next_hi_value]) VALUES (N'EHR', 4)
INSERT [dbo].[hibernate_sequences] ([sequence_name], [sequence_next_hi_value]) VALUES (N'Patient', 5)
/****** Object:  Table [dbo].[Assignments]    Script Date: 05/21/2010 16:22:41 ******/
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
	[LastEditBy] [varchar](15) NULL,
 CONSTRAINT [PK_Assignments] PRIMARY KEY CLUSTERED 
(
	[AssignID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AddressData]    Script Date: 05/21/2010 16:22:41 ******/
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
	[LastEditBy] [varchar](15) NULL,
 CONSTRAINT [PK_AddressData] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Message]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Message](
	[message_id] [bigint] NOT NULL,
	[ehr_id] [bigint] NOT NULL,
	[message_type_id] [bigint] NULL,
	[creation_timestamp] [datetime] NULL,
	[message] [varchar](max) NULL,
	[parent] [bigint] NULL,
	[last_edit] [datetime] NULL,
	[last_edit_by] [varchar](15) NULL,
 CONSTRAINT [PK_Message] PRIMARY KEY CLUSTERED 
(
	[message_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [form].[BillingStrip]    Script Date: 05/21/2010 16:22:41 ******/
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
/****** Object:  Table [dbo].[Echarts_User]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Echarts_User](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [varchar](15) NOT NULL,
	[staff_id] [varchar](4) NOT NULL,
	[password] [varbinary](50) NULL,
	[role_id] [int] NULL,
	[ext_perm] [varbinary](4) NULL,
	[staff_name] [varchar](35) NULL,
	[program] [varchar](3) NULL,
	[hire_date] [datetime] NULL,
	[status] [varchar](10) NULL,
	[term_date] [datetime] NULL,
	[office] [varchar](15) NULL,
	[office_phone] [varchar](12) NULL,
	[office_ext] [varchar](4) NULL,
	[staff_description] [varchar](25) NULL,
	[staff_npi] [varchar](15) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_User_StaffId] UNIQUE NONCLUSTERED 
(
	[staff_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_User_Username] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Echarts_User] ON
INSERT [dbo].[Echarts_User] ([id], [username], [staff_id], [password], [role_id], [ext_perm], [staff_name], [program], [hire_date], [status], [term_date], [office], [office_phone], [office_ext], [staff_description], [staff_npi]) VALUES (1, N'ihilt', N'5597', NULL, 1, NULL, N'Ian Hilt', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Echarts_User] OFF
/****** Object:  View [dbo].[VTicklerList]    Script Date: 05/21/2010 16:22:41 ******/
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
/****** Object:  View [dbo].[VSuffix]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VSuffix]
AS
SELECT     Value AS Suffix, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'Suffix')
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
/****** Object:  View [dbo].[VRace]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VRace]
AS
SELECT     Value AS Race, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'Race')
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
/****** Object:  View [dbo].[VMessageType]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VMessageType]
AS
SELECT     Value AS MessageType, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'MessageType')
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
/****** Object:  View [dbo].[VMaritalStatus]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VMaritalStatus]
AS
SELECT     TOP (100) PERCENT Value AS MaritalStatus, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'MaritalStatus')
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
/****** Object:  View [dbo].[VMainAddress]    Script Date: 05/21/2010 16:22:41 ******/
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
/****** Object:  View [dbo].[VLivingArrangement]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VLivingArrangement]
AS
SELECT     Value AS LivingArrangement, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'LivingArrangement')
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
/****** Object:  View [dbo].[VLevelofCare]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VLevelofCare]
AS
SELECT     Value AS LevelofCare, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'LevelofCare')
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
/****** Object:  View [dbo].[VIncomeSource]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VIncomeSource]
AS
SELECT     Value AS IncomeSource, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'IncomeSource')
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
/****** Object:  View [dbo].[VGender]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VGender]
AS
SELECT     Value AS Gender, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'Gender')
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
/****** Object:  View [dbo].[VEmployment]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VEmployment]
AS
SELECT     Value AS Employment, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'Employment')
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
/****** Object:  View [dbo].[VEducationType]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VEducationType]
AS
SELECT     Value AS EducationType, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'EducationType')
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
/****** Object:  View [dbo].[VEducationLevel]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VEducationLevel]
AS
SELECT     Value AS EducationLevel, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'EducationLevel')
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
/****** Object:  View [dbo].[VDisposition]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VDisposition]
AS
SELECT     Value AS Disposition, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'Disposition')
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
/****** Object:  View [dbo].[VCounty]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VCounty]
AS
SELECT     Descriptor AS County, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'County')
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
/****** Object:  View [dbo].[VCaseStatus]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/****** Script for SelectTopNRows command from SSMS  ******/
CREATE VIEW [dbo].[VCaseStatus]
AS
SELECT     Value AS CaseStatus, Descriptor
FROM         dbo.Codes
WHERE     (column_name = 'CaseStatus')
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
/****** Object:  View [dbo].[VActiveAssignments]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VActiveAssignments]
AS
SELECT     dbo.Assignments.AssignID, dbo.Assignments.PATID, dbo.Assignments.CreateDate, dbo.Assignments.FromDate, dbo.Assignments.ToDate, 
                      dbo.Assignments.Disposition, dbo.Assignments.Type, dbo.Assignments.StaffId, dbo.Assignments.Program, dbo.Assignments.LastEdit, dbo.Assignments.LastEditBy, 
                      dbo.[Echarts_User].staff_name
FROM         dbo.Assignments INNER JOIN
                      dbo.[Echarts_User] ON dbo.Assignments.StaffId = dbo.[Echarts_User].staff_id
WHERE     (dbo.Assignments.Disposition = 1)
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
         Begin Table = "Assignments"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 125
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "User"
            Begin Extent = 
               Top = 6
               Left = 236
               Bottom = 125
               Right = 403
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
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VActiveAssignments'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'VActiveAssignments'
GO
/****** Object:  Table [dbo].[UserSessionMap]    Script Date: 05/21/2010 16:22:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserSessionMap](
	[SessionId] [bigint] NOT NULL,
	[UserId] [bigint] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  StoredProcedure [dbo].[getStaffId]    Script Date: 05/21/2010 16:22:41 ******/
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
	SELECT @staffid = (SELECT staff_id FROM [Echarts_User] WHERE [Echarts_User].Id = (SELECT UserId FROM [UserSessionMap] inner join [session_id_log] on [UserSessionMap].SessionId=[session_id_log].Id WHERE [session_id_log].session_id =@sessionid));
END
GO
/****** Object:  Default [DF_Referral_UPID]    Script Date: 05/21/2010 16:22:40 ******/
ALTER TABLE [dbo].[Referral] ADD  CONSTRAINT [DF_Referral_UPID]  DEFAULT ('01396') FOR [upid]
GO
/****** Object:  Default [DF_Tickler_LastEdit]    Script Date: 05/21/2010 16:22:40 ******/
ALTER TABLE [dbo].[Tickler] ADD  CONSTRAINT [DF_Tickler_LastEdit]  DEFAULT (getutcdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_SystemLog_EventDate]    Script Date: 05/21/2010 16:22:40 ******/
ALTER TABLE [dbo].[SystemLog] ADD  CONSTRAINT [DF_SystemLog_EventDate]  DEFAULT (getutcdate()) FOR [EventDate]
GO
/****** Object:  Default [DF_ProgressNote_EntryDate]    Script Date: 05/21/2010 16:22:40 ******/
ALTER TABLE [form].[ProgressNote] ADD  CONSTRAINT [DF_ProgressNote_EntryDate]  DEFAULT (getutcdate()) FOR [EntryDate]
GO
/****** Object:  Default [DF_DiagnosisData_LastEdit]    Script Date: 05/21/2010 16:22:40 ******/
ALTER TABLE [dbo].[DiagnosisData] ADD  CONSTRAINT [DF_DiagnosisData_LastEdit]  DEFAULT (getutcdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_Assignments_CreateDate]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [dbo].[Assignments] ADD  CONSTRAINT [DF_Assignments_CreateDate]  DEFAULT (getutcdate()) FOR [CreateDate]
GO
/****** Object:  Default [DF_Assignments_LastEdit]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [dbo].[Assignments] ADD  CONSTRAINT [DF_Assignments_LastEdit]  DEFAULT (getutcdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_AddressData_LastEdit]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [dbo].[AddressData] ADD  CONSTRAINT [DF_AddressData_LastEdit]  DEFAULT (getutcdate()) FOR [LastEdit]
GO
/****** Object:  Default [DF_BillingStrip_EntryDate]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [form].[BillingStrip] ADD  CONSTRAINT [DF_BillingStrip_EntryDate]  DEFAULT (getutcdate()) FOR [EntryDate]
GO
/****** Object:  ForeignKey [FK_Ehr_Ehr]    Script Date: 05/21/2010 16:22:39 ******/
ALTER TABLE [dbo].[Ehr]  WITH CHECK ADD  CONSTRAINT [FK_Ehr_Ehr] FOREIGN KEY([subject_id])
REFERENCES [dbo].[Patient] ([patient_id])
GO
ALTER TABLE [dbo].[Ehr] CHECK CONSTRAINT [FK_Ehr_Ehr]
GO
/****** Object:  ForeignKey [FK_Demographics_PatientId]    Script Date: 05/21/2010 16:22:39 ******/
ALTER TABLE [dbo].[Demographics]  WITH CHECK ADD  CONSTRAINT [FK_Demographics_PatientId] FOREIGN KEY([patient_id])
REFERENCES [dbo].[Patient] ([patient_id])
GO
ALTER TABLE [dbo].[Demographics] CHECK CONSTRAINT [FK_Demographics_PatientId]
GO
/****** Object:  ForeignKey [FK_Alert_Patient]    Script Date: 05/21/2010 16:22:39 ******/
ALTER TABLE [dbo].[Alert]  WITH CHECK ADD  CONSTRAINT [FK_Alert_Patient] FOREIGN KEY([patient_id])
REFERENCES [dbo].[Patient] ([patient_id])
GO
ALTER TABLE [dbo].[Alert] CHECK CONSTRAINT [FK_Alert_Patient]
GO
/****** Object:  ForeignKey [FK_Patient_Ehr]    Script Date: 05/21/2010 16:22:39 ******/
ALTER TABLE [dbo].[Patient]  WITH CHECK ADD  CONSTRAINT [FK_Patient_Ehr] FOREIGN KEY([ehr_id])
REFERENCES [dbo].[Ehr] ([ehr_id])
GO
ALTER TABLE [dbo].[Patient] CHECK CONSTRAINT [FK_Patient_Ehr]
GO
/****** Object:  ForeignKey [FK_Referral_Referral]    Script Date: 05/21/2010 16:22:40 ******/
ALTER TABLE [dbo].[Referral]  WITH CHECK ADD  CONSTRAINT [FK_Referral_Referral] FOREIGN KEY([ehr_id])
REFERENCES [dbo].[Ehr] ([ehr_id])
GO
ALTER TABLE [dbo].[Referral] CHECK CONSTRAINT [FK_Referral_Referral]
GO
/****** Object:  ForeignKey [FK_ProgressNote_ProgressNote]    Script Date: 05/21/2010 16:22:40 ******/
ALTER TABLE [form].[ProgressNote]  WITH CHECK ADD  CONSTRAINT [FK_ProgressNote_ProgressNote] FOREIGN KEY([TicketNum])
REFERENCES [form].[ProgressNote] ([TicketNum])
GO
ALTER TABLE [form].[ProgressNote] CHECK CONSTRAINT [FK_ProgressNote_ProgressNote]
GO
/****** Object:  ForeignKey [FK_Message_Codes]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK_Message_Codes] FOREIGN KEY([message_type_id])
REFERENCES [dbo].[Codes] ([code_id])
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK_Message_Codes]
GO
/****** Object:  ForeignKey [FK_Message_Ehr]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK_Message_Ehr] FOREIGN KEY([ehr_id])
REFERENCES [dbo].[Ehr] ([ehr_id])
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK_Message_Ehr]
GO
/****** Object:  ForeignKey [FK_BillingStrip_ProgressNote]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [form].[BillingStrip]  WITH CHECK ADD  CONSTRAINT [FK_BillingStrip_ProgressNote] FOREIGN KEY([ID])
REFERENCES [form].[ProgressNote] ([TicketNum])
GO
ALTER TABLE [form].[BillingStrip] CHECK CONSTRAINT [FK_BillingStrip_ProgressNote]
GO
/****** Object:  ForeignKey [FK_BillingStrip_ServiceCodes]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [form].[BillingStrip]  WITH CHECK ADD  CONSTRAINT [FK_BillingStrip_ServiceCodes] FOREIGN KEY([Service])
REFERENCES [dbo].[ServiceCodes] ([Service])
GO
ALTER TABLE [form].[BillingStrip] CHECK CONSTRAINT [FK_BillingStrip_ServiceCodes]
GO
/****** Object:  ForeignKey [FK_Role_Role_Id]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [dbo].[Echarts_User]  WITH CHECK ADD  CONSTRAINT [FK_Role_Role_Id] FOREIGN KEY([role_id])
REFERENCES [dbo].[Role] ([role_id])
GO
ALTER TABLE [dbo].[Echarts_User] CHECK CONSTRAINT [FK_Role_Role_Id]
GO
/****** Object:  ForeignKey [FK_UserSessionMap_SessionIdLog]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [dbo].[UserSessionMap]  WITH CHECK ADD  CONSTRAINT [FK_UserSessionMap_SessionIdLog] FOREIGN KEY([SessionId])
REFERENCES [dbo].[session_id_log] ([id])
GO
ALTER TABLE [dbo].[UserSessionMap] CHECK CONSTRAINT [FK_UserSessionMap_SessionIdLog]
GO
/****** Object:  ForeignKey [FK_UserSessionMap_UserId]    Script Date: 05/21/2010 16:22:41 ******/
ALTER TABLE [dbo].[UserSessionMap]  WITH CHECK ADD  CONSTRAINT [FK_UserSessionMap_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[Echarts_User] ([id])
GO
ALTER TABLE [dbo].[UserSessionMap] CHECK CONSTRAINT [FK_UserSessionMap_UserId]
GO
